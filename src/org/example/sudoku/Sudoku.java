package org.example.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

public class Sudoku extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private static final String TAG = "Sudoku";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Set up click listeners for all the buttons
        View continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);
        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.continue_button:
			startGame(Game.DIFFICULTY_CONTINUE);
			break;
		case R.id.about_button:
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
		case R.id.new_button:
			openNewGameDialog();
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, Prefs.class));
			return true;
			//More items go here, if any...
		}
		return false;
	}
	
	   /** Ask the user what difficulty level they want */
	   private void openNewGameDialog() {
	      new AlertDialog.Builder(this)
	           .setTitle(R.string.new_game_title)
	           .setItems(R.array.difficulty,
	            new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialoginterface,
	                     int i) {
	                  startGame(i);
	               }
	            })
	           .show();
	   }
	   
	   /** Start a new game with the given difficulty level */
	   private void startGame(int i) {
	      Log.d(TAG, "clicked on " + i);
	      Intent intent = new Intent(Sudoku.this, Game.class);
	      intent.putExtra(Game.KEY_DIFFICULTY, i);
	      startActivity(intent);
	   }
	   
	   @Override
	   protected void onResume() {
		   super.onResume();
		   Music.play(this, R.raw.main);
	   }
	   
	   @Override
	   protected void onPause() {
		   super.onPause();
		   Music.stop(this);
	   }
}