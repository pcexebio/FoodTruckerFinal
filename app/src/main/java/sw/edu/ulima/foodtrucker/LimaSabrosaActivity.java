package sw.edu.ulima.foodtrucker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LimaSabrosaActivity extends Activity implements View.OnClickListener{

    Button butMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lima_sabrosa);

        butMenu=(Button)findViewById(R.id.butMenu);
        butMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MenuLimaSabrosaActivity.class);
        startActivity(intent);
    }
}
