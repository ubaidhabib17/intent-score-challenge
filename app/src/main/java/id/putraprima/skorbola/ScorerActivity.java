package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;

public class ScorerActivity extends AppCompatActivity {
    EditText scorerText;


    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        scorerText = findViewById(R.id.scorerName);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_CANCELED){
            return;
        }

        if(requestCode == 1){
            if(data != null){
                try {
                    imageUri1 = data.getData();
                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri1);
                    homeImage.setImageBitmap(bitmap1);
                }catch (IOException e){
                    Toast.makeText(this,"Can't Load Image",Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }else if(requestCode == 2){
            if(data != null){
                try {
                    imageUri2 = data.getData();
                    bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri2);
                    awayImage.setImageBitmap(bitmap2);
                }catch (IOException e){
                    Toast.makeText(this,"Can't Load Image",Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleScorerName(View view) {
        String inputScorer = scorerText.getText().toString();

        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra("inputScorer", inputScorer);
        startActivity(intent);
    }
}
