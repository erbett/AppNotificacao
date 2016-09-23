/* 
 * Copyright 2016 Erbett H. R. Oliveira, Inc. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 		
 * 		1. Redistributions of source code must retain the above copyright notice, this list of
 * 			conditions and the following disclaimer.
 * 
 * 		2. Redistributions in binary form must reproduce the above copyright notice, this list
 * 		   of conditions and the following disclaimer in the documentation and/or other materials
 *         provided with the distribution.
 *         
 * THIS SOFTWARE IS PROVIDED BY ERBETT HINTON RIBEIRO OLIVEIRA, INC. ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, AND LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ERBETT HINTON RIBEIRO OLIVEIRA, INC. OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package br.com.curso.appnotificacao;

import com.example.appnotificacao.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NotificacaoActivity extends Activity implements OnClickListener {
	
	private EditText mensagem;
	private EditText titulo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mensagem = (EditText) findViewById(R.id.edit_view_mensagem);
		titulo = (EditText) findViewById(R.id.edit_view_titulo);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		
		if (mensagem.getText().length() > 0 && titulo.getText().length() > 0){
			Notificacao notificacao = new Notificacao(titulo.getText(), mensagem.getText());
			criaNotificacao(notificacao);
		}else{
			Toast.makeText(this, R.string.notificacao_toast, Toast.LENGTH_LONG).show();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void criaNotificacao(Notificacao notificacao){
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this.getApplicationContext(), NotificacaoActivity.class), 0);
		
		Notification notificaction = new Notification(R.drawable.ic_launcher, notificacao.getTitulo(), System.currentTimeMillis());
		notificaction.setLatestEventInfo(this, notificacao.getTitulo(),notificacao.getMensagem(), pendingIntent);
		
		//remove a notificacao quando o usuario selecionar
		notificaction.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(R.string.app_name, notificaction);
	}

}