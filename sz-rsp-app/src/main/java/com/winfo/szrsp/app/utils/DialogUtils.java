package com.winfo.szrsp.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winfo.szrsp.app.R;

/**
 * 对话框工具箱
 *
 */
public class DialogUtils {
	
private static AnimationDrawable animationDrawable ;
	public interface DialogOnClickListenner{
		void btnConfirmClick(Dialog dialog);
		void btnCancelClick(Dialog dialog);
	}

	/**
	 * 弹出用户可交互的对话框
	 * @param context 上下文
	 * @param title	对话框标题
	 * @param content 对话框提示内容
	 * @param dialogOnClickListenner 接口回调  没有做非null判断  所以接口不能为null 必须实现该接口来操作对话框
	 */
	public static Dialog showDialog(final Context context , String title , String content,final DialogOnClickListenner dialogOnClickListenner){
		final Dialog dialog = new Builder(context).create();
		dialog.setCancelable(true);//
		dialog.setCanceledOnTouchOutside(false);//
		final View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
		Button positiveButton = (Button) view.findViewById(R.id.dialog_btn_ok);
		Button negativeButton = (Button) view.findViewById(R.id.dialog_btn_cancel);
		TextView dialogTitle = (TextView) view.findViewById(R.id.dialog_title);
		TextView dialogContent = (TextView) view.findViewById(R.id.dialog_content);
		dialogTitle.setText(title);
		dialogContent.setText(content);

		positiveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogOnClickListenner.btnConfirmClick(dialog);
			}
		});
		negativeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogOnClickListenner.btnCancelClick(dialog);
			}
		});
		dialog.show();
		dialog.setContentView(view);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int width = (int) (dm.widthPixels*0.8);
		int height = ViewGroup.LayoutParams.WRAP_CONTENT;
		dialog.getWindow().setLayout(width ,height);
		return dialog;
	}

	/**
	 * 弹出用户可交互的对话框
	 * @param context 上下文
	 * @param title	标题
	 * @param content 内容
	 * @param btnConfirmText 确定按钮的文本
	 * @param btnCancelText 取消按钮的文本
	 * @param dialogOnClickListenner 按钮的事件回调
	 */
	public static void showDialog(final Context context , String title , String content,String btnConfirmText , String btnCancelText ,final DialogOnClickListenner dialogOnClickListenner){
		final Dialog dialog = new Builder(context).create();
//		Window window = dialog.getWindow();
//		window.setWindowAnimations(R.style.dialogstyle);
		dialog.setCancelable(true);//
		dialog.setCanceledOnTouchOutside(false);//
		final View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
		Button btnConfirm = (Button) view.findViewById(R.id.dialog_btn_ok);
		Button btnCancel = (Button) view.findViewById(R.id.dialog_btn_cancel);
		TextView dialogTitle = (TextView) view.findViewById(R.id.dialog_title);
		TextView dialogContent = (TextView) view.findViewById(R.id.dialog_content);
		dialogTitle.setText(title);
		dialogContent.setText(content);
		btnConfirm.setText(btnConfirmText);
		btnCancel.setText(btnCancelText);
		btnConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogOnClickListenner.btnConfirmClick(dialog);
			}
		});
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogOnClickListenner.btnCancelClick(dialog);
			}
		});
		dialog.show();
		dialog.setContentView(view);
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int width = (int) (dm.widthPixels*0.8);
		int height = ViewGroup.LayoutParams.WRAP_CONTENT;
		dialog.getWindow().setLayout(width ,height);
	}

	/**
	 * 获取一个类似加载的对话框
	 * @param context 上下文
	 * @param msg 文字说明
	 * @return 对话框的对象
	 */
	public static Dialog createLoadingDialog(Context context, String msg) {
		
		/*
		 * 获得view填充器对象
		 */
        LayoutInflater inflater = LayoutInflater.from(context);
        /*
         * 得到加载view
         */
        View v = inflater.inflate(R.layout.loading_dialog, null);
        /*
         * 加载布局  
         */
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        
        /*
         *  main.xml中的ImageView  
         */
        final ImageView imgViewLoading = (ImageView) v.findViewById(R.id.img);
        
       
        
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        /*
         *  加载动画  
         */
//        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
//                context, R.anim.loading_animation);  
        /*
         *  使用ImageView显示动画  
         */
//        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
        if(msg != null && !msg.equals("")){
        	tipTextView.setText(msg);// 设置加载信息  
        }
  
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 可以用“返回键”取消  
        loadingDialog.setCanceledOnTouchOutside(false);//
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        
        loadingDialog.setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
				animationDrawable    = (AnimationDrawable) imgViewLoading.getDrawable();
					animationDrawable.start();
			}
		});
        
        
        
        loadingDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				 animationDrawable = (AnimationDrawable) imgViewLoading.getDrawable();
					animationDrawable.stop();
			}
		});
//		WindowManager.LayoutParams lp = loadingDialog.getWindow().getAttributes();
//		lp.alpha = 0.3f;
//		loadingDialog.getWindow().setAttributes(lp);
        return loadingDialog;
    }  

	/**
	 * 显示一个对话框
	 * @param activity Activity对象，需要依托于Activity所在的主线程才能显示提示框
	 * @param title 标题
	 * @param message 消息
	 * @param confrimButton 确认按钮
	 * @param confrimButtonCliskListener 确认按钮点击监听器
	 * @param centerButton 中间按钮
	 * @param centerButtonCliskListener 中间按钮点击监听器
	 * @param cancelButton 取消按钮
	 * @param cancelButtonCliskListener 取消按钮点击监听器
	 * @param onShowListener 显示监听器
	 * @param cancelable 是否允许通过点击返回按钮或者点击对话框之外的位置关闭对话框
	 * @param onCancelListener 取消监听器
	 * @param onDismissListener 销毁监听器
	 * @return 对话框
	 */
	public static AlertDialog showAlert(Activity activity, String title, String message,
                                        String confrimButton, DialogInterface.OnClickListener confrimButtonCliskListener,
                                        String centerButton, DialogInterface.OnClickListener centerButtonCliskListener,
                                        String cancelButton, DialogInterface.OnClickListener cancelButtonCliskListener,
                                        OnShowListener onShowListener,
                                        boolean cancelable, DialogInterface.OnCancelListener onCancelListener,
                                        OnDismissListener onDismissListener){
		Builder promptBuilder = new Builder(activity);
		if(title != null){
			promptBuilder.setTitle(title);
		}
		if(message != null){
			promptBuilder.setMessage(message);
		}
		if(confrimButton != null){
			promptBuilder.setPositiveButton(confrimButton, confrimButtonCliskListener);
		}
		if(centerButton != null){
			promptBuilder.setNeutralButton(centerButton, centerButtonCliskListener);
		}
		if(cancelButton != null){
			promptBuilder.setNegativeButton(cancelButton, cancelButtonCliskListener);
		}
		promptBuilder.setCancelable(true);
		if(cancelable){
			promptBuilder.setOnCancelListener(onCancelListener);
		}
		AlertDialog alertDialog = promptBuilder.create();
		alertDialog.setOnDismissListener(onDismissListener);
		alertDialog.setOnShowListener(onShowListener);
		alertDialog.show();
		return alertDialog;
	}

	/**
	 * 显示一个对话框
	 * @param activity Activity对象，需要依托于Activity所在的主线程才能显示提示框
	 * @param title 标题
	 * @param message 消息
	 * @param confrimButton 确认按钮
	 * @param confrimButtonCliskListener 确认按钮点击监听器
	 * @param cancelButton 取消按钮
	 * @param cancelButtonCliskListener 取消按钮点击监听器
	 * @return 对话框
	 */
	public static AlertDialog showAlert(Activity activity, String title, String message,
                                        String confrimButton, DialogInterface.OnClickListener confrimButtonCliskListener,
                                        String cancelButton, DialogInterface.OnClickListener cancelButtonCliskListener){
		return showAlert(activity, title, message, confrimButton, confrimButtonCliskListener, null, null, cancelButton, cancelButtonCliskListener, null, true, null, null);
	}

	/**
	 * 显示一个提示框
	 * @param activity Activity对象，需要依托于Activity所在的主线程才能显示提示框
	 * @param message 提示的消息
	 * @param confrimButton 确定按钮的名字
	 */
	public static AlertDialog showPrompt(Activity activity, String message, String confrimButton){
		return showAlert(activity, null, message, confrimButton, null, null, null, null, null, null, true, null, null);
	}

	/**
	 * 显示一个提示框
	 * @param activity Activity对象，需要依托于Activity所在的主线程才能显示提示框
	 * @param message 提示的消息
	 */
	public static AlertDialog showPrompt(Activity activity, String message){
		return showAlert(activity, null, message, "OK", null, null, null, null, null, null, true, null, null);
	}

	/**
	 * 设置给定的对话框点击是否关闭
	 * @param alertDialog 给定的对话框
	 * @param close 点击是否关闭
	 */
	public static void setDialogClickClose(AlertDialog alertDialog, boolean close){
		alertDialog.dismiss();
	}
	
	/**
	 * 将view 添加到对话框中
	 * @param activity  Activity对象，需要依托于Activity所在的主线程才能显示提示框
	 * @param view	要添加的view对象
	 * @param title	现实的标题
	 * @param message	现实的内容
	 * @param confrimButton	确定按钮
	 * @param confrimButtonCliskListener	点击确定后的事件
	 * @param cancelButton	取消按钮
	 * @param cancelButtonCliskListener	取消按钮的事件
	 */
	public static void showViewDialog(Activity activity, View view, String title, String message, String confrimButton, DialogInterface.OnClickListener confrimButtonCliskListener,
                                      String cancelButton, DialogInterface.OnClickListener cancelButtonCliskListener){
		Builder builder = new Builder(activity);
		builder.setTitle(title)
		.setMessage(message)
		.setView(view)
		.setPositiveButton(confrimButton, confrimButtonCliskListener)
		.setNegativeButton(cancelButton, cancelButtonCliskListener)
		.create().show();
	}
}
