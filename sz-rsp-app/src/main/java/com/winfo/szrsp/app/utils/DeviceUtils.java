package com.winfo.szrsp.app.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;

import net.vidageek.mirror.dsl.Mirror;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 项目名: gdmsaec-app
 * 包名: com.winfo.gdmsaec.app.utils
 * 类名: DeviceUtils
 * 创建者: yanfeijun
 * 创建时间: 2015-10-20	下午4:21:29
 * 描述: 设备工具箱，提供与设备硬件相关的工具方法
 */
public class DeviceUtils {
    /**
     * 获取屏幕尺寸
     *
     * @param activity actiivty
     * @return 返回一个长度为2的int数组，int[0]是宽；int[1]是高
     */
    public static int[] getScreenSize(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return new int[]{width, height};
    }

    /**
     * @param offset 方向传感器的偏移量
     * @return 方向信息
     */
    public static String[] getDirection(double offset) {
        String[] direction = new String[2];
        int mOffset = (int) (offset + .5f);
        if (mOffset >= 360) {
            mOffset -= 360;
        }
        if (mOffset > 0 && mOffset < 90) {
            direction[0] = mOffset + "";
            direction[1] = "东北";
        } else if (mOffset == 90) {
            direction[0] = "90";
            direction[1] = "正东";
        } else if (mOffset > 90 && mOffset < 180) {
            direction[0] = mOffset + "";
            direction[1] = "东南";
        } else if (mOffset == 180) {
            direction[0] = "180";
            direction[1] = "正南";
        } else if (mOffset > 180 && mOffset < 270) {
            direction[0] = mOffset + "";
            direction[1] = "西南";
        } else if (mOffset == 270) {
            direction[0] = "270";
            direction[1] = "正西";
        } else if (mOffset > 270 && mOffset < 360) {
            direction[0] = mOffset + "";
            direction[1] = "西北";
        } else {
            direction[0] = "0";
            direction[1] = "正北";
        }
        return direction;
    }

    /**
     * 获取手机状态蓝的高度
     *
     * @param context 上下文对象
     * @return 高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商 honor
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取设备名称
     *
     * @return 名称   huawei
     */
    public static String getDeviceName() {
        return android.os.Build.MANUFACTURER;

    }

    /**
     * 设备型号
     *
     * @return 型号
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }


    /**
     * 获取系统版本
     *
     * @return 版本号
     */
    public static String getAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取序列号
     *
     * @return 序列号
     */
    @SuppressLint("HardwareIds")
    public static String getSerialNumber() {
        return android.os.Build.SERIAL;
    }

    /**
     * 获取手机号码
     *
     * @param context 上下文
     * @return 手机号
     */
    @SuppressLint("HardwareIds")
    public static String getPhoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        assert telephonyManager != null;
        return telephonyManager.getLine1Number();
    }

    /**
     * 获取iemi号
     *
     * @param context 上下文
     * @return imei号
     */
    @SuppressLint("HardwareIds")
    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.getMeid();
            } else {
                return tm.getDeviceId();
            }
        }
        return null;
    }

    /**
     * 获取CPU型号
     *
     * @return cpu
     */
    public static String getCpuName() {
        return android.os.Build.HARDWARE;
    }

    /**
     * 获取蓝牙地址
     *
     * @return 地址
     */
    public static String getBluetoothAddress() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Object bluetoothManagerService = new Mirror().on(bluetoothAdapter).get().field("mService");
        if (bluetoothManagerService == null) {
            return null;
        }
        Object address = new Mirror().on(bluetoothManagerService).invoke().method("getAddress").withoutArgs();
        if (address != null && address instanceof String) {
            return (String) address;
        } else {
            return null;
        }
    }


    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";

    /**
     * 获取MAC地址
     *
     * @return MAC地址
     */
    @SuppressLint("HardwareIds")
    public static String getMACAddress(Context context) {
        WifiManager wifiMan = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        assert wifiMan != null;
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        if (wifiInf != null && marshmallowMacAddress.equals(wifiInf.getMacAddress())) {
            String result;
            try {
                result = getAdressMacByInterface();
                if (result != null) {
                    return result;
                } else {
                    result = getAddressMacByFile(wifiMan);
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (wifiInf != null && wifiInf.getMacAddress() != null) {
                return wifiInf.getMacAddress();
            } else {
                return "";
            }
        }
        return marshmallowMacAddress;
    }

    private static String getAdressMacByInterface() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", b));
                    }
                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
        String ret;
        int wifiState = wifiMan.getWifiState();
        wifiMan.setWifiEnabled(true);
        File fl = new File(fileAddressMac);
        FileInputStream fin = new FileInputStream(fl);
        ret = crunchifyGetStringFromStream(fin);
        fin.close();
        boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
        wifiMan.setWifiEnabled(enabled);
        return ret;
    }

    private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
        if (crunchifyStream != null) {
            Writer crunchifyWriter = new StringWriter();

            char[] crunchifyBuffer = new char[2048];
            try {
                Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            } finally {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        } else {
            return "No Contents";
        }
    }

    /**
     * 获取网络类型
     *
     * @return 网络类型
     */
    public static String getNetworkType(Context context) {
        //获取系统的网络服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //如果当前没有网络
        if (null == connManager)
            return null;
        //获取当前网络类型，如果为空，返回无网络
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return null;
        }
        // 判断是不是连接的是不是wifi
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return "wifi";
                }
        }
        // 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (null != networkInfo) {
            NetworkInfo.State state = networkInfo.getState();
            String strSubTypeName = networkInfo.getSubtypeName();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    switch (activeNetInfo.getSubtype()) {
                        //如果是2g类型
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            return "2G";
                        //如果是3g类型
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            return "3G";
                        //如果是4g类型
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            return "4G";
                        default:
                            //中国移动 联通 电信 三种3G制式
                            if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                                return "3G";
                            } else {
                                return null;
                            }
                    }
                }
        }
        return null;
    }


    /**
     * 获取运营商
     *
     * @return 运营商
     */
    public static String getNetworkOperatorName(Context context) {
        String ProvidersName = null;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        assert telephonyManager != null;
        @SuppressLint("HardwareIds") String IMSI = telephonyManager.getSubscriberId();
        if (IMSI != null) {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
                ProvidersName = "中国移动";
            } else if (IMSI.startsWith("46001") || IMSI.startsWith("46006")) {
                ProvidersName = "中国联通";
            } else if (IMSI.startsWith("46003")) {
                ProvidersName = "中国电信";
            }
            return ProvidersName;
        } else {
            return null;
        }
    }

    /**
     * 获取ip地址
     *
     * @return ip地址
     */
    public static String getIpAddress(Context context) {
        assert (context.getSystemService(Context.CONNECTIVITY_SERVICE)) != null;
        NetworkInfo info = null;
        if ((context.getSystemService(Context.CONNECTIVITY_SERVICE)) != null) {
            info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        }
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                assert wifiManager != null;
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                return intIP2StringIP(wifiInfo.getIpAddress());
            }
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     */
    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    /**
     * 获取手机存储
     *
     * @return 存储大小
     */
    public static String getDeviceStorage(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        }
        long blockCountLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockCountLong = statFs.getBlockCountLong();
        }
        long size = blockCountLong * blockSizeLong;
        return Formatter.formatFileSize(context, size);
    }

    /**
     * 获取设备运行内存
     *
     * @return ram 大小
     */
    public static String getDeviceRam(Context context) {
        try {
            FileInputStream fis = new FileInputStream(new File("/proc/meminfo"));
            //包装一个一行行读取的流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            //取到所有的内存信息
            String memTotal = bufferedReader.readLine();
            StringBuilder sb = new StringBuilder();
            for (char c : memTotal.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    sb.append(c);
                }
            }
            //为了方便格式化 所以乘以1024
            long totalMemory = Long.parseLong(sb.toString()) * 1024;
            return Formatter.formatFileSize(context, totalMemory);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取设备meid
     *
     * @return meid
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceMeid(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Method method;
        try {
            assert manager != null;
            method = manager.getClass().getMethod("getDeviceId", int.class);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
//            String imei1 = manager.getDeviceId();
//            String imei2 = (String) method.invoke(manager, 1);
            return (String) method.invoke(manager, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}