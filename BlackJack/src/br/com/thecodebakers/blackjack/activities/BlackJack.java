/*
 * Copyright (C) 2011 The Code Bakers
 * Authors: Cleuton Sampaio e Francisco Rodrigues
 * e-mail: thecodebakers@gmail.com
 * Project: https://code.google.com/p/open-source-android-blackjack/
 * Site: http://thecodebakers.blogspot.com
 *
 * Licensed under the GNU GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://gplv3.fsf.org/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Cleuton Sampaio e Francisco Rogrigues - thecodebakers@gmail.com
 */
package br.com.thecodebakers.blackjack.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.thecodebakers.blackjack.R;
import br.com.thecodebakers.blackjack.business.BlackBO;

public class BlackJack extends Activity {
	
	private BlackBO blackBO;
	private static final String TAG = "BlackJack";
	
	private int posicaoCartaBanca=0;
	private int posicaoCartaPlayer=0;
	private int somabanca =0;
	private int somaplayer=0;
	private Integer cartaBanca ;
	private Integer cartaPlayer;
	private boolean som=true;
	private MediaPlayer media = null;
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /*
        cartaAnim = AnimationUtils.loadAnimation(BlackJack.this, R.anim.carta_anim);
        cartaAnim2 = AnimationUtils.loadAnimation(BlackJack.this, R.anim.carta_anim2);
        
        //imageAdapter = new ImageAdapter(this);
        //galeria de imagens 2
        gridview2 = (GridView) findViewById(R.id.gridview2);
        gridview2.setAdapter(imageAdapter);
        gridview2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,long id){
				
				if (v instanceof ImageView){
					ImageView imageView = (ImageView) v;
					imageView.setImageResource(cartas[position]);
					Animation scaleAnim = AnimationUtils.loadAnimation(BlackJack.this, R.anim.carta_anim);
					imageView.setAnimation(scaleAnim);
					scaleAnim.startNow();
				}
			}
		});*/
        try{
        	media = MediaPlayer.create(this.getApplicationContext(), R.raw.audio);
        	media.start();
        }catch  (Exception e){
        	e.printStackTrace();
        }
        
        blackBO = BlackBO.getInstance(this.getApplicationContext());
               
    }
	
	@Override
	protected void onStart() {
    	super.onStart();
	}
    
    @Override
	public void onBackPressed() {
		moveTaskToBack(true);
	}
    /**
     * button hitme
     * @param view
     */
    public void hitMe (View view) {
    	Log.d(TAG, "hit Me if u are the player..."); 
    	
    	blackBO.hitme();
    	cartaBanca = blackBO.getCartaBanca();
    	cartaPlayer = blackBO.getCartaPlayer() ; 
    	    	
    	Log.d(TAG, "imageView cartaPlayer ID" + cartaPlayer);
    	Log.d(TAG, "imageView cartaBanca ID" + cartaBanca);
    	
    	String pontos = null;
    	
    	//Cartas da Banca
    	if (cartaBanca  != null){
    		
    		ImageView imageView = (ImageView) this. findViewById(posicaoCartasBanca[posicaoCartaBanca]);
	    	imageView.setImageResource(cartaBanca);
	    	Animation cartaAnim1 = AnimationUtils.loadAnimation(BlackJack.this, R.anim.carta_anim);
			imageView.setAnimation(cartaAnim1);
			cartaAnim1.startNow();
			posicaoCartaBanca++;
			
			//seta o total de pontos da banca
			TextView pontosBanca = (TextView) this.findViewById(R.id.textViewPntBanca); 
			this.somabanca = blackBO.getSomabanca();
			pontos = String.valueOf( somabanca );
			pontosBanca.setText(pontos);
			
    	}
    	
		//Suas cartas
    	if (cartaPlayer != null){
    		
			ImageView imageView = (ImageView) this. findViewById(posicaoCartasPlayer[posicaoCartaPlayer]);//R.id.img1Grid2
    		imageView.setImageResource(cartaPlayer);
    		Animation cartaAnim2 = AnimationUtils.loadAnimation(BlackJack.this, R.anim.carta_anim2);
			imageView.setAnimation(cartaAnim2);
			cartaAnim2.startNow();
			posicaoCartaPlayer++;
			
			//seta o total de pontos do jogador
			TextView pontosPlayer = (TextView) findViewById(R.id.textViewPntSuasCartas); 
			this.somaplayer = blackBO.getSomaplayer();
			pontos = String.valueOf( somaplayer );
			pontosPlayer.setText(pontos);
    	}
    	
		if (blackBO.getMessage() != null){
			this.mensagem (blackBO.getMessage().getText());
		}
    	Log.d(TAG, "End of hitMe ");
    	
	}
    /**
     * Button stand
     * @param view
     */
    public void stand (View view) {
    	Log.d(TAG, "stand the game..."); 
    	blackBO.stand();
    	String pontos = null;
    	TextView pontosBanca = null;
    	ArrayList<Integer> cartaBancaStand = blackBO.getCartasSorteadasStand();
    	
    	//Cartas da Banca
    	for (Integer cartaStand : cartaBancaStand) {
    		
    		if (cartaStand  != null){
        		ImageView imageView = (ImageView) this. findViewById(posicaoCartasBanca[posicaoCartaBanca]);
    	    	imageView.setImageResource(cartaStand);
    	    	Animation cartaAnim1 = AnimationUtils.loadAnimation(BlackJack.this, R.anim.carta_anim);
    			imageView.setAnimation(cartaAnim1);
    			cartaAnim1.startNow();
    			posicaoCartaBanca++;
    			//seta o total de pontos da banca
    			pontosBanca = (TextView) this.findViewById(R.id.textViewPntBanca); 
    			this.somabanca = blackBO.getSomabanca();
    			pontos = String.valueOf( somabanca );
    			pontosBanca.setText(pontos);
    			
        	}
        	
		}
    	    	
    	if (blackBO.getMessage() != null){
			this.mensagem (blackBO.getMessage().getText());
		}
	}
    
    /**
     * button new game
     * @param view
     */
    public void novoJogo (View view) {
    	Log.d(TAG, "starting new game...");
    	reloadActivity();
    	Log.d(TAG, "game started."); 
	}
    
    private void reloadActivity(){
    	Log.d(TAG, "reloading activity...");
    	posicaoCartaBanca=0;
    	posicaoCartaPlayer=0;
    	somabanca =0;
    	somaplayer=0;
    	this.cartaBanca=0;
    	this.cartaPlayer=0;
    	
    	blackBO.novo();
    	Intent i = new Intent(this, BlackJack.class);
    	startActivity(i);
		this.finish();
    }
    
    //array de inteiros com as imagens das cartas da banca
    private Integer[]  posicaoCartasBanca = {
	   		 R.id.img1Grid1, R.id.img2Grid1, R.id.img3Grid1, 
	   		 R.id.img4Grid1, R.id.img5Grid1, R.id.img6Grid1, 
	   		 R.id.img7Grid1, R.id.img8Grid1, R.id.img9Grid1, 
	   		 R.id.img10Grid1
    };

	//array de inteiros com as imagens das cartas do jogador
	private Integer[]  posicaoCartasPlayer = {
			 R.id.img1Grid2, R.id.img2Grid2, R.id.img3Grid2, 
	   		 R.id.img4Grid2, R.id.img5Grid2, R.id.img6Grid2, 
	   		 R.id.img7Grid2, R.id.img8Grid2, R.id.img9Grid2, 
	   		 R.id.img10Grid2
	};

	private void mensagem (String texto) {
    	new AlertDialog.Builder(this).setMessage(texto)
        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	reloadActivity();
            	return;
            } 
         }).show();     	
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_main, menu);
	    return true;
	}

    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mnuAjuda:
			Intent i = new Intent (this.getApplicationContext(), Ajuda.class);
			this.startActivity(i);
			return true;
		default: return super.onOptionsItemSelected(item); 
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (media != null) {
			if (media.isPlaying()) {
				media.pause();
			}
		}
		Log.d(TAG, "pause the sound.");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (media != null) {
			if (!media.isPlaying()) {
				media.start();
			}
		}	
		Log.d(TAG, "play the sound.");
	}
	
	
}