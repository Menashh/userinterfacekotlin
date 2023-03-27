package com.example.meni_baninkotlin
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var age: String? = null;
    var kind: String? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customButton = findViewById<Button>(R.id.orderButton)


        customButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.order, null)
            builder.setView(dialogView)
            builder.show()

            val adultChild = dialogView.findViewById<RadioGroup>(R.id.AdultorChild)
            val Part = dialogView.findViewById<RadioGroup>(R.id.Part)
            val datePicker = dialogView.findViewById<Button>(R.id.dateButton)
            val getTicktes = dialogView.findViewById<Button>(R.id.GetTickets)
            val confirmPerchase = dialogView.findViewById<Button>(R.id.areyousure)

            val alphaAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)
            confirmPerchase.startAnimation(alphaAnim)

            adultChild.setOnCheckedChangeListener { group, checkId ->
                if (checkId == -1) {
                    age = null
                } else {
                    val radioButton = dialogView.findViewById<RadioButton>(checkId)
                    age = radioButton.text.toString()
                }
            }
            Part.setOnCheckedChangeListener { group, checkId ->
                if (checkId == -1) {
                    kind = null
                } else {
                    val radioButton = dialogView.findViewById<RadioButton>(checkId)
                    kind = radioButton.text.toString()
                }
            }

            var selectyear = ""
            var selectmonth = ""
            var selectday = ""
            var date = getString(R.string.date)
            datePicker.setOnClickListener {
                val c = Calendar.getInstance()
                val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    selectyear = year.toString()
                    selectmonth = (month + 1).toString()
                    selectday = day.toString()

                    if (!selectyear.isNullOrEmpty() || !selectmonth.isNullOrEmpty() || !selectday.isNullOrEmpty()) {
                        date = "Date: $selectyear/$selectmonth/$selectday\n"
                    }
                }

                val dtb = DatePickerDialog(
                    this, listener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(
                        Calendar.DAY_OF_MONTH
                    )
                )
                dtb.show()
            }
            getTicktes.setOnClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.apply {setMessage("You have choose a ticket for " +
                        "Spongebob Squarepants $kind for one $age at $date")}
                val message = builder.create()
                message.show()
            }
            confirmPerchase.setOnClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.apply {
                    setTitle(getString(R.string.confirmperchase))
                    setMessage(getString(R.string.ticket))
                    setCancelable(false)
                    setIcon(R.drawable.baseline_check_24)
                    setPositiveButton(R.string.yes) { p0, p1, ->
                        Toast.makeText(
                            context, "Order details:\n" +
                                    "one ticket for: " + age + "\n" +
                                    "You choose: " + kind + "\n" +
                                    "On date:$date", Toast.LENGTH_LONG
                        ).show()
                    }
                    setNegativeButton(R.string.no) { p0, p1 -> }
                    val dialog = builder.create()
                    dialog.show()
                }
            }

        }
    }
}





