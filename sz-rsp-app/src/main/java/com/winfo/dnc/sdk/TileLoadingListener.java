package com.winfo.dnc.sdk;

import android.graphics.Bitmap;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 切片下载监听
 * @author King
 *
 */
class TileLoadingListener implements ImageLoadingListener {
	private TileInfo tf;
	private WinfoDNCView winfoDNCView;
	private int layerNum;
	TileLoadingListener(TileInfo tf, WinfoDNCView winfoDNCView, int layerNum){
		this.tf=tf;
		this.layerNum = layerNum;
		this.winfoDNCView=winfoDNCView;
	}

	@Override
	public void onLoadingStarted(String imageUri, View view) {

	}

	@Override
	public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//		MapTileDecoderHttp.loader.stop();
//		if(failReason.getType() == FailReason.FailType.IO_ERROR){
//			//图片不存在 加载失败
////			System.out.println("图片加载失败");
//		}else if(failReason.getType() == FailReason.FailType.NETWORK_DENIED){
//			//网络连接失败  加载失败
////			MapTileDecoderHttp.decode(imageUri, this);
//			//ToastUtils.showToast(GdmsaecApplication.getContext(), "网络连接失败");
//		}else if(failReason.getType() == FailReason.FailType.OUT_OF_MEMORY){
////			System.out.println("内存溢出");
//			//ToastUtils.showToast(GdmsaecApplication.getContext(), "内存溢出");
//		}else if(failReason.getType() == FailReason.FailType.UNKNOWN){
////			System.out.println("未知错误");
//		}else if(failReason.getType() == FailReason.FailType.DECODING_ERROR){
//			//ToastUtils.showToast(GdmsaecApplication.getContext(), "DECODING_ERROR");
////			System.out.println("DECODING_ERROR");
//		}
	}

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		if(layerNum == 0){
			tf.bitmap=loadedImage;
		}else if(layerNum == 1){
			tf.bitmap2 = loadedImage;
		}
		winfoDNCView.invalidate();
	}

	@Override
	public void onLoadingCancelled(String imageUri, View view) {

	}

}