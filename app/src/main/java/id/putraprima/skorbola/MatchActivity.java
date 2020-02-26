package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    TextView homeName;
    TextView awayName;
    TextView awayScore;
    TextView homeScore;
    ImageView homeLogo;
    ImageView awayLogo;
    Uri uri1;
    Uri uri2;
    Bitmap bitmap1;
    Bitmap bitmap2;
    String homeTeam;
    String awayTeam;
    String scorerText;
    int scoreHome = 0;
    int scoreAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeName = findViewById(R.id.txt_home);
        awayName = findViewById(R.id.txt_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        awayScore = findViewById(R.id.score_away);
        homeScore = findViewById(R.id.score_home);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        Bundle extras = getIntent().getExtras();
        homeTeam = extras.getString("inputHome");
        awayTeam = extras.getString("inputAway");
        scorerText = extras.getString("inputScorer");

        if (extras != null) {
            // TODO: display value here

            uri1 = Uri.parse(extras.getString("logoHome"));
            uri2 = Uri.parse(extras.getString("logoAway"));
            bitmap1 = null;
            bitmap2 = null;

            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
            } catch (IOException e) {
                e.printStackTrace();
            }

            homeName.setText(homeTeam);
            awayName.setText(awayTeam);
            homeLogo.setImageBitmap(bitmap1);
            awayLogo.setImageBitmap(bitmap2);

    }
    }

    //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
    public void scoreHomeHandler(View view){
        scoreHome += 1;
        homeScore.setText(String.valueOf(scoreHome));
        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra("homeScore", scoreHome);
        startActivity(intent);
    }

    public void scoreAwayHandler(View view) {
        scoreAway +=1;
        awayScore.setText(String.valueOf(scoreAway));
        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra("awayScore", scoreAway);
        startActivity(intent);
    }

    //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1


    //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
}
