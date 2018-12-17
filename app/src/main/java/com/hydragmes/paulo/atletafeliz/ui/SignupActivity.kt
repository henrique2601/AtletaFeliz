package com.hydragmes.paulo.atletafeliz.ui

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.hydragmes.paulo.atletafeliz.Model.BRState
import com.hydragmes.paulo.atletafeliz.helper.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import com.google.firebase.database.FirebaseDatabase
import com.hydragmes.paulo.atletafeliz.Model.Athlete
import com.hydragmes.paulo.atletafeliz.R


class SignupActivity : AppCompatActivity() {

    companion object {
        val TAG: String = SignupActivity::class.java.simpleName
    }

    var stateList: List<BRState> = listOf()
    var cityList: List<BRState> = listOf()
    val sportArray: Array<String> = arrayOf("Selecione o esporte", "Arremesso de peso", "Basquete", "Canoagem", "Ciclismo", "futebol", "Karate", "Volley")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "Complete seu cadastro"
        setContentView(R.layout.activity_signup)

        fillState()
        configSportSpinner()
        configCitySpinner()
        configDateInput()
        configConfirmButton()
        configText()
    }

    private fun configText() {
        bioText.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(v)
            }
        })

    }

    private fun configConfirmButton() {
        confirmButton.setOnClickListener {
            completeSignup()
        }
    }

    private fun completeSignup() {
        if (!validateFields()) {
            return
        }

        var athlete = Athlete.create()
        val user = FirebaseAuth.getInstance().currentUser
        athlete.uid = user!!.uid
        athlete.name = user.displayName!!
        athlete.email = user.email!!
        athlete.state = stateSpinner.selectedItem.toString()
        athlete.city = citySpinner.selectedItem.toString()
        athlete.sport = sportSpinner.selectedItem.toString()
        athlete.birthday = SimpleDateFormat("dd.MM.yyyy").parse(birthdayText.text.toString())
        athlete.bio = bioText.text.toString()

        val path = "users/" + user!!.uid

        FirebaseDatabase.getInstance().reference.child(path).setValue(athlete)
    }

    private fun validateFields(): Boolean {
        if (stateSpinner.selectedItemPosition == 0) {
            Toast.makeText(this, "Selecione o estado", Toast.LENGTH_SHORT).show()
            return false
        }

        if (citySpinner.selectedItemPosition == 0) {
            Toast.makeText(this, "Selecione a cidade", Toast.LENGTH_SHORT).show()
            return false
        }

        if (sportSpinner.selectedItemPosition == 0) {
            Toast.makeText(this, "Selecione a cidade", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun configStateSpinner() {
        val stringArray =  listOf("Selecione o seu estado:") + stateList.map { estado -> estado.nome }.toTypedArray()
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray)
        stateSpinner.adapter = aa
        stateSpinner.isVerticalScrollBarEnabled = true
        stateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                if (pos != 0) {
                    val selectedState = stateList[pos - 1]
                    fillCities(selectedState.id.toString())
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
            }
        }

    }

    private fun configDateInput() {
        birthdayText.text = "Selecione sua data de nascimento"

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            birthdayText.text = sdf.format(cal.time)

        }

        birthdayText.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun configCitySpinner() {
        val stringArray =  listOf("Selecione a sua cidade:") + cityList.map { estado -> estado.nome }.toTypedArray()
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray)
        citySpinner.adapter = aa
        citySpinner.isVerticalScrollBarEnabled = true
    }

    private fun configSportSpinner() {
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, sportArray)
        sportSpinner.adapter = aa
        sportSpinner.isVerticalScrollBarEnabled = true
    }

    private fun fillState() {
        RetrofitInitializer().stateService().list().enqueue(object: Callback<List<BRState>> {

            override fun onResponse(call: Call<List<BRState>>, response: Response<List<BRState>>) {
                Log.i(TAG, "Success")
                stateList = response.body()!!.sortedBy { it.nome }
                configStateSpinner()
            }

            override fun onFailure(call: Call<List<BRState>>, t: Throwable?) {
                Log.i(TAG, t!!.message)
            }
        })
    }

    private fun fillCities(StateId:String) {
        RetrofitInitializer().cityService().list(StateId).enqueue(object: Callback<List<BRState>> {

            override fun onResponse(call: Call<List<BRState>>, response: Response<List<BRState>>) {
                Log.i(TAG, "Success")
                cityList = response.body()!!.sortedBy { it.nome }
                configCitySpinner()
            }

            override fun onFailure(call: Call<List<BRState>>, t: Throwable?) {
                Log.i(TAG, t!!.message)
            }
        })
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager!!.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
