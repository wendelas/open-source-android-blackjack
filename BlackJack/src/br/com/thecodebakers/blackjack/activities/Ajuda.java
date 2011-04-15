package br.com.thecodebakers.blackjack.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import br.com.thecodebakers.blackjack.R;


public class Ajuda extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ajuda);
	}

	@Override
	public void onBackPressed() {
		this.finish();
	}

	public void web(View view) {
		Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://thecodebakers.blogspot.com"));
		startActivity(viewIntent); 
	}
	
	public void fechar(View view) {
		finish();
	}	
}
