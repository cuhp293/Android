package com.example.dialogexamplest4

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_show).setOnClickListener {

//      AlertDialog.Builder(this)
//        .setIcon(R.drawable.ic_android_black_24dp)
//        .setTitle("Test dialog")
//        .setMessage("This is a test dialog")
//        .setPositiveButton("Yes",
//          { _, _ -> Log.v("TAG", "Yes was clicked")})
//        .setNegativeButton("No",
//          { _, _ -> Log.v("TAG", "No was clicked")})
//        .setNeutralButton("Cancel", null)
//        .show()
//        .setCanceledOnTouchOutside(false)

            val items = mutableListOf<String>()
            for (i in 0..50)
                items.add("Item $i")

//      AlertDialog.Builder(this)
//        .setTitle("Select an option")
//        .setItems(items.toTypedArray(),
//          { _, i: Int -> Log.v("TAG", "Selected: $i")})
//        .show()

//      AlertDialog.Builder(this)
//        .setTitle("Select an option")
//        .setSingleChoiceItems(items.toTypedArray(), 0,
//          { _, i: Int -> Log.v("TAG", "Selected: $i")})
//        .setPositiveButton("OK",
//          { _, _ -> /* Process the selection */ })
//        .setNegativeButton("Cancel", null)
//        .show()

            val selections = BooleanArray(items.size)

//      AlertDialog.Builder(this)
//        .setTitle("Select an option")
//        .setMultiChoiceItems(items.toTypedArray(), selections,
//          { _, i: Int, b: Boolean -> Log.v("TAG", "Item $i: $b") })
//        .setPositiveButton("OK",
//          { _, _ -> /* Process the selection */ })
//        .setNegativeButton("Cancel", null)
//        .show()


//      val dialogView = LayoutInflater.from(this@MainActivity)
//        .inflate(R.layout.layout_alert_dialog, null)
//
//      val editHoten = dialogView.findViewById<EditText>(R.id.edit_hoten)
//      val editMssv = dialogView.findViewById<EditText>(R.id.edit_mssv)
//
//      AlertDialog.Builder(this)
//        .setTitle("Nhap thong tin sinh vien")
//        .setView(dialogView)
//        .setPositiveButton("OK", {
//          _, _ ->
//          val hoten = editHoten.text.toString()
//          val mssv = editMssv.text.toString()
//          Log.v("TAG", "$hoten: $mssv")
//        })
//        .setNegativeButton("Cancel", null)
//        .create().show()


//      val dialog = Dialog(this@MainActivity)
//      dialog.setContentView(R.layout.layout_dialog)
//      val editHoten = dialog.findViewById<EditText>(R.id.edit_hoten)
//      val editMssv = dialog.findViewById<EditText>(R.id.edit_mssv)
//      dialog.findViewById<Button>(R.id.button_ok).setOnClickListener {
//        val hoten = editHoten.text.toString()
//        val mssv = editMssv.text.toString()
//        Log.v("TAG", "$hoten: $mssv")
//        dialog.dismiss()
//      }
//      dialog.findViewById<Button>(R.id.button_cancel).setOnClickListener {
//        dialog.dismiss()
//      }
//      dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
//      dialog.show()

//      TestDialogFragment().show(supportFragmentManager, "DIALOG")

//      val c = Calendar.getInstance()
//      val mYear = c.get(Calendar.YEAR)
//      val mMonth = c.get(Calendar.MONTH)
//      val mDay = c.get(Calendar.DAY_OF_MONTH)
//
//      DatePickerDialog(this,
//        { _, year: Int, month: Int, day: Int -> Log.v("TAG", "$year - $month - $day")},
//        mYear, mMonth, mDay).show()

//      TimePickerDialog(this,
//        { _, hour: Int, minute: Int -> Log.v("TAG", "$hour:$minute")},
//        16, 30, false).show()

//      Toast.makeText(this@MainActivity, "Hello Android", Toast.LENGTH_LONG)
//        .show()

            Snackbar.make(it, "Hello Android", Snackbar.LENGTH_LONG)
                .setAction("UNDO", {})
                .show()
        }
    }
}