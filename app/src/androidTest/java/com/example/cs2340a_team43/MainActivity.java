package com.example.cs2340a_team43;

import com.example.cs2340a_team43.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configuration);
        Button easyButton = findViewById(R.id.easyButton);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userText = binding.editTextTextPersonName.getText().toString();
                binding.textView.setText(userText);
            '
            }
        })
    }
}
