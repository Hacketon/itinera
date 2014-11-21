package br.com.itinera.itineramobile;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnEntrar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Referencia botão tela
		btnEntrar = (Button)findViewById(R.id.btnEntrar);
		
		btnEntrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Click do botão entrar
				Intent i = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(i);				
			}
		});
		
	     
 	}
	
	

}
