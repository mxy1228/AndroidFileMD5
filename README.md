# 获取文件MD5值

传入File对象或文件路径即可计算文件MD5值。
提供了同步和异步方法。异步方法由RxJava包装。**使用同步方法注意不要在UI主线程中调用，因为计算MD5值较为耗时**
使用示例：
```java
String filePath = "";
        SecurityHelper.getInstance()
                .fileMD5Async(filePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        // TODO
                        unsubscribe();
                    }
                });
```