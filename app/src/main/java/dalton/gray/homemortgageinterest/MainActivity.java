package dalton.gray.homemortgageinterest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCE_NAME = "LOAN";

    float payment, loan;
    int years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText monthlyPayment = (EditText) findViewById(R.id.monthlyPayment_et);
        final EditText numYears = (EditText) findViewById(R.id.numYears_et);
        final EditText loanAmount = (EditText) findViewById(R.id.loanAmount_et);

        Button compute = (Button) findViewById(R.id.compute_btn);
        compute.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                payment = Float.parseFloat(monthlyPayment.getText().toString());
                years = Integer.parseInt(numYears.getText().toString());
                loan = Float.parseFloat(loanAmount.getText().toString());

                final SharedPreferences loanPref = getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = loanPref.edit();
                editor.putFloat("monthlyPayment", payment);
                editor.putInt("numYears", years);
                editor.putFloat("loanAmount", loan);
                editor.commit();

                startActivity(new Intent(MainActivity.this, InterestActivity.class));
            }
        });
    }
}
