package com.example.projectdb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    EditText etName, etPhone;
    Button btnAdd;
    RecyclerView recyclerView;
    ContactAdapter adapter;

    List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "contacts-db").allowMainThreadQueries().build();

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> {
            Contact contact = new Contact();
            contact.name = etName.getText().toString();
            contact.phone = etPhone.getText().toString();
            db.contactDao().insert(contact);

            contacts.clear();
            contacts.addAll(db.contactDao().getAll());
            adapter.notifyDataSetChanged();
        });

        contacts.addAll(db.contactDao().getAll());
        adapter.notifyDataSetChanged();
    }
}
