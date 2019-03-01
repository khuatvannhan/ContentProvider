package com.example.android.contentproviderexample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.example.android.contentproviderexample.model.Contact
import com.example.android.contentproviderexample.model.Contact.Builder
import kotlinx.android.synthetic.main.activity_danh_ba.*
import android.widget.Toast



class DanhBaActivity : AppCompatActivity() {
    val dsDanhBa = ArrayList<Contact>()
    lateinit var adapter: ContactAdapter
    val PERMISSIONS_REQUEST_READ_CONTACTS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_ba)
        addControls()
        addEvents()
        showContacts()
    }

    private fun showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), PERMISSIONS_REQUEST_READ_CONTACTS)
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            showAllContactFromDevice()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts()
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun showAllContactFromDevice() {
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null, null)
        dsDanhBa.clear()
        while (cursor.moveToNext()) {
            val tenCotName = ContactsContract.Contacts.DISPLAY_NAME
            val tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER
            val vtTenCotName = cursor.getColumnIndex(tenCotName)
            val vtTenCotPhone = cursor.getColumnIndex(tenCotPhone)
            val name = cursor.getString(vtTenCotName)
            val phone = cursor.getString(vtTenCotPhone)
            val contact = Contact.Builder().setName(name).setPhone(phone).build()
            dsDanhBa.add(contact)
        }

        adapter.notifyDataSetChanged()
    }

    private fun addEvents() {

    }

    private fun addControls() {
        if (!::adapter.isInitialized) {
            adapter = ContactAdapter(dsDanhBa, baseContext, LayoutInflater.from(baseContext))
        }
        lvDanhBa.adapter = adapter
    }
}
