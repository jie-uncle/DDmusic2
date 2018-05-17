package com.jie.ddmusic2.http;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jie.ddmusic2.util.Final;

import org.json.JSONException;
import org.json.JSONObject;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.FileDownloader;
import org.wlf.filedownloader.listener.OnDetectBigUrlFileListener;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.android.volley.Request.Method.GET;

/**
 * Created by jie on 2017/8/2.
 */

public class DownLoadUtil {
    static OnFileDownloadStatusListener mOnFileDownloadStatusListener;

    static RequestQueue requestQueue;

    public static void regester(final download download) {
        mOnFileDownloadStatusListener = new OnSimpleFileDownloadStatusListener() {
            @Override
            public void onFileDownloadStatusRetrying(DownloadFileInfo downloadFileInfo, int retryTimes) {
                // 正在重试下载（如果你配置了重试次数，当一旦下载失败时会尝试重试下载），retryTimes是当前第几次重试
            }

            @Override
            public void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo) {
                // 等待下载（等待其它任务执行完成，或者FileDownloader在忙别的操作）
            }

            @Override
            public void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo) {
                // 准备中（即，正在连接资源）
            }

            @Override
            public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
                // 已准备好（即，已经连接到了资源）
            }

            @Override
            public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long
                    remainingTime) {
                // 正在下载，downloadSpeed为当前下载速度，单位KB/s，remainingTime为预估的剩余时间，单位秒
            }

            @Override
            public void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo) {
                // 下载已被暂停
            }

            @Override
            public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
                //下载成功
                download.success();
            }

            @Override
            public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason) {
                // 下载失败了，详细查看失败原因failReason，有些失败原因你可能必须关心

                String failType = failReason.getType();
                String failUrl = failReason.getUrl();// 或：failUrl = url，url和failReason.getType()会是一样的

                if (OnFileDownloadStatusListener.FileDownloadStatusFailReason.TYPE_URL_ILLEGAL.equals(failType)) {
                    // 下载failUrl时出现url错误
                    download.err("url错误");
                } else if (OnFileDownloadStatusListener.FileDownloadStatusFailReason.TYPE_STORAGE_SPACE_IS_FULL.equals(failType)) {
                    // 下载failUrl时出现本地存储空间不足
                    download.err("本地存储空间不足");
                } else if (OnFileDownloadStatusListener.FileDownloadStatusFailReason.TYPE_NETWORK_DENIED.equals(failType)) {
                    // 下载failUrl时出现无法访问网络
                    download.err("无法访问网络");
                } else if (OnFileDownloadStatusListener.FileDownloadStatusFailReason.TYPE_NETWORK_TIMEOUT.equals(failType)) {
                    // 下载failUrl时出现连接超时
                    download.err("连接超时");
                } else {
                    // 更多错误....
                }

                // 查看详细异常信息
                Throwable failCause = failReason.getCause();// 或：failReason.getOriginalCause()

                // 查看异常描述信息
                String failMsg = failReason.getMessage();// 或：failReason.getOriginalCause().getMessage()
            }
        };
// 注册监听器
        FileDownloader.registerDownloadStatusListener(mOnFileDownloadStatusListener);

    }

    public static void downloadLrc(String url, final String songname, final String singer) {
        FileDownloader.detect(url, new OnDetectBigUrlFileListener() {
            @Override
            public void onDetectNewDownloadFile(String url, String fileName, String saveDir, long fileSize) {
                // 如果有必要，可以改变文件名称fileName和下载保存的目录saveDir
                FileDownloader.createAndStart(url, Final.lyricPath, songname + "-" + singer + ".lrc");
            }

            @Override
            public void onDetectUrlFileExist(String url) {
                // 继续下载，自动会断点续传（如果服务器无法支持断点续传将从头开始下载）
                FileDownloader.start(url);
            }

            @Override
            public void onDetectUrlFileFailed(String url, DetectBigUrlFileFailReason failReason) {
                // 探测一个网络文件失败了，具体查看failReason
            }
        });
    }

    public static void unregester() {
        // 取消注册下载状态监听器(一般在fragment或activity的onDestroy方法中取消注册)
        if (mOnFileDownloadStatusListener != null)
            FileDownloader.unregisterDownloadStatusListener(mOnFileDownloadStatusListener);
    }


    public static void downloadLrc(final File file, final download download, String songid) {

        Log.e("haha",songid);
        try {
        if (!file.exists()) {

                file.createNewFile();
            }
                requestQueue.add(new StringRequest(
                        GET
                        , "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.song.lry&songid=" + songid
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String lrcContent = jsonObject.getString("lrcContent");
                            Log.e("haha",lrcContent);
                            FileOutputStream fileInputStream = new FileOutputStream(file);
                            fileInputStream.write(lrcContent.getBytes());
                            fileInputStream.close();
                            download.success();
                        } catch (JSONException e) {
                            download.err("加载失败");
                        } catch (FileNotFoundException e) {
                            download.err("加载失败");
                        } catch (IOException e) {
                            download.err("加载失败");
                        }
                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        download.err(error.toString());
                    }
                }


                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public static void initVolley(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

}
