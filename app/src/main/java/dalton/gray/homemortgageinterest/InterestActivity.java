package dalton.gray.homemortgageinterest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class InterestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        TextView monthlyPayment = (TextView) findViewById(R.id.monthlyPayment_tv);
        ImageView yearsImage = (ImageView) findViewById(R.id.years_iv);

        SharedPreferences loanPref = getSharedPreferences(MainActivity.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        float payment = loanPref.getFloat("monthlyPayment", 0);
        int years = loanPref.getInt("numYears", 0);
        float loan = loanPref.getFloat("loanAmount", 0);

        float interestPaid = (payment * (years * 12)) - loan;
        DecimalFormat currencyUSD = new DecimalFormat("$###,###.##");
        monthlyPayment.setText("Total Interest Paid: " + currencyUSD.format(interestPaid));

        if(years <= 10) {
            yearsImage.setImageResource(R.drawable.ten);
        } else if(years <= 20) {
            yearsImage.setImageResource(R.drawable.twenty);
        } else {
            yearsImage.setImageResource(R.drawable.thirty);
        }
    }
}
