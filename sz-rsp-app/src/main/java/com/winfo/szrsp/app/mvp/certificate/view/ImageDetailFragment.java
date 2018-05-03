package com.winfo.szrsp.app.mvp.certificate.view;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winfo.szrsp.app.R;
import com.winfo.szrsp.app.utils.DialogUtils;

import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.decode.ImageAttrs;
import me.panpf.sketch.request.CancelCause;
import me.panpf.sketch.request.DisplayListener;
import me.panpf.sketch.request.ErrorCause;
import me.panpf.sketch.request.ImageFrom;

public class ImageDetailFragment extends Fragment {
    public static int mImageLoading;//占位符图片
    public static boolean mNeedDownload = false;//默认不支持下载
    private String mImageUrl;
    private SketchImageView sketchImageView;
    private Dialog dialog;
//    private PhotoViewAttacher mAttacher;
//    private Bitmap mBitmap;

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment imageDetailFragment = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        imageDetailFragment.setArguments(args);

        return imageDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
        dialog = DialogUtils.createLoadingDialog(getActivity(),"加载中...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_image_detail, container, false);
        sketchImageView = (SketchImageView) v.findViewById(R.id.image);
//        mAttacher = new PhotoViewAttacher(mImageView);
//        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mNeedDownload) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                    builder.setMessage("保存图片");
//                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ImageUtil.saveImage(getActivity(), mImageUrl, mBitmap);
//                        }
//                    });
//                    builder.create().show();
//                }
//                return false;
//            }
//        });
//        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
//
//            @Override
//            public void onPhotoTap(View arg0, float arg1, float arg2) {
//                getActivity().finish();
//            }
//        });
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(mImageUrl)) {
            sketchImageView.setZoomEnabled(true);
            sketchImageView.setShowDownloadProgressEnabled(true);
            sketchImageView.getZoomer().zoom(3f, false);
            sketchImageView.setDisplayListener(new DisplayListener() {
                @Override
                public void onStarted() {
                    // 只有在需要进入非主线程加载图片时才会回调 onStarted() 方法
                   dialog.show();
                }

                @Override
                public void onCompleted(Drawable drawable, ImageFrom imageFrom, ImageAttrs imageAttrs) {
                    dialog.dismiss();
                }

                @Override
                public void onError(ErrorCause errorCause) {
                    dialog.dismiss();
                }

                @Override
                public void onCanceled(CancelCause cancelCause) {
                    dialog.dismiss();
                }
            });
            sketchImageView.displayImage(mImageUrl);


        } else {
            sketchImageView.setImageResource(mImageLoading);
        }
    }
}
