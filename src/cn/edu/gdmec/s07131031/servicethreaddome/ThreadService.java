package cn.edu.gdmec.s07131031.servicethreaddome;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ThreadService extends Service {
	private Runnable backgroundWork = new Runnable(){

		@Override
		public void run() {
			try {
			while(!Thread.interrupted()){
				double randomDouble = Math.random();
				MainActivity.UpdateGUI(randomDouble);
					Thread.sleep(1000);
				} 
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	
	private Thread WorkThread;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this,"服务创建了",1000).show();
		 WorkThread = new Thread(null,backgroundWork,"Workthread");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this,"服务停止了",1000).show();
		WorkThread.interrupt();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(this,"服务启动了",1000).show();
		if(!WorkThread.isAlive()){
			WorkThread.start();
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
