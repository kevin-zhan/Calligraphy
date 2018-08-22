package tech.zymx.calligraphy.model;

/**
 * Created by kevinzhan@tencent.com on 2018/8/20
 */
public class DbtbImageModel implements ImageUrlProvider{

    private String[] mImgList;
    private static final DbtbImageModel sInstance = new DbtbImageModel();

    public static DbtbImageModel getInstance() {
        return sInstance;
    }

    @Override
    public String getImageUrl(int index) {
        if (index < 0 || index >= mImgList.length) {
            return "";
        }
        return mImgList[index];
    }

    @Override
    public int getImageCount() {
        return mImgList.length;

    }

    private DbtbImageModel() {
        mImgList = new String[]{
                "https://s1.ax1x.com/2018/08/21/P4Q3Y6.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QgXQ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Q8fK.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QJSO.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Q1Fx.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QYlD.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Qamd.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Qt6e.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QNOH.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Qrff.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Qd0A.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QwTI.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QBkt.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QDtP.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QOB9.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Qyp8.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Q61S.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Qc6g.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QRmj.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Qf7n.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Q5t0.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QW0s.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QX7R.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Q4kq.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QIhV.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QTpT.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Q71U.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QHcF.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QbX4.jpg",
                "https://s1.ax1x.com/2018/08/21/P4QLnJ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4ln9f.jpg",
                "https://s1.ax1x.com/2018/08/21/P4l0u4.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lKgS.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lu38.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lMjg.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lluQ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4l1Bj.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lNCV.jpg",
                "https://s1.ax1x.com/2018/08/21/P4l3Hs.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lGEn.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lJNq.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lY40.jpg",
                "https://s1.ax1x.com/2018/08/21/P4l64x.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lU3T.jpg",
                "https://s1.ax1x.com/2018/08/21/P4l5bd.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lagU.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lBDJ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lsER.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lgC6.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lhKe.jpg",
                "https://s1.ax1x.com/2018/08/21/P4l28K.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lRgO.jpg",
                "https://s1.ax1x.com/2018/08/21/P4l4DH.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lTUI.jpg",
                "https://s1.ax1x.com/2018/08/21/P4l75t.jpg",
                "https://s1.ax1x.com/2018/08/21/P4loVA.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lL28.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lq8f.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lbPP.jpg",
                "https://s1.ax1x.com/2018/08/21/P4lOxS.jpg",
                "https://s1.ax1x.com/2018/08/21/P43Pwd.jpg",
                "https://s1.ax1x.com/2018/08/21/P43CeH.jpg",
                "https://s1.ax1x.com/2018/08/21/P43pOe.jpg",
                "https://s1.ax1x.com/2018/08/21/P43EfP.jpg",
                "https://s1.ax1x.com/2018/08/21/P43iTA.jpg",
                "https://s1.ax1x.com/2018/08/21/P43AYt.jpg",
                "https://s1.ax1x.com/2018/08/21/P43kFI.jpg",
                "https://s1.ax1x.com/2018/08/21/P43el8.jpg",
                "https://s1.ax1x.com/2018/08/21/P43ZSf.jpg",
                "https://s1.ax1x.com/2018/08/21/P43m6S.jpg",
                "https://s1.ax1x.com/2018/08/21/P43KmQ.jpg",
                "https://s1.ax1x.com/2018/08/21/P43nOg.jpg",
                "https://s1.ax1x.com/2018/08/21/P43Mwj.jpg",
                "https://s1.ax1x.com/2018/08/21/P43Dt1.jpg",
                "https://s1.ax1x.com/2018/08/21/P431kn.jpg",
                "https://s1.ax1x.com/2018/08/21/P43QTs.jpg",
                "https://s1.ax1x.com/2018/08/21/P43YlT.jpg",
                "https://s1.ax1x.com/2018/08/21/P433Yq.jpg",
                "https://s1.ax1x.com/2018/08/21/P438f0.jpg",
                "https://s1.ax1x.com/2018/08/21/P43JpV.jpg",
                "https://s1.ax1x.com/2018/08/21/P43t6U.jpg",
                "https://s1.ax1x.com/2018/08/21/P43d0J.jpg",
                "https://s1.ax1x.com/2018/08/21/P43NXF.jpg",
                "https://s1.ax1x.com/2018/08/21/P43am4.jpg",
                "https://s1.ax1x.com/2018/08/21/P43BkR.jpg",
                "https://s1.ax1x.com/2018/08/21/P43w79.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Gnaj.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GuIs.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GQGq.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GmZQ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GZqg.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GMin.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GGsU.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GlR0.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GtZ4.jpg",
                "https://s1.ax1x.com/2018/08/21/P4G1zV.jpg",
                "https://s1.ax1x.com/2018/08/21/P4G8MT.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GNdJ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GJLF.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GwJ1.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GUo9.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GdiR.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GyLD.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GrQK.jpg",
                "https://s1.ax1x.com/2018/08/21/P4G0Rx.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GBz6.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GssO.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Goy8.jpg",
                "https://s1.ax1x.com/2018/08/21/P4G5SP.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GIQf.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GHeg.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GTOS.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GbwQ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JCmF.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Gqoj.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GXYn.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GOFs.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GjWq.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JPw4.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GxS0.jpg",
                "https://s1.ax1x.com/2018/08/21/P4GzlV.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JSyT.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JpOU.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JEf1.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JZSx.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JiTJ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JAYR.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Jkk9.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Jel6.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Jm6K.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JQTH.jpg",
                "https://s1.ax1x.com/2018/08/21/P4J8fI.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JnOO.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JKmD.jpg",
                "https://s1.ax1x.com/2018/08/21/P4JM0e.jpg",
                "https://s1.ax1x.com/2018/08/21/P4J3tA.jpg",
                "https://s1.ax1x.com/2018/08/21/P4J1kd.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aSZ8.jpg",
                "https://s1.ax1x.com/2018/08/21/P4UvsP.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Uxqf.jpg",
                "https://s1.ax1x.com/2018/08/21/P4a9Ig.jpg",
                "https://s1.ax1x.com/2018/08/21/P4UjMt.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aMi4.jpg",
                "https://s1.ax1x.com/2018/08/21/P4apdS.jpg",
                "https://s1.ax1x.com/2018/08/21/P4amZT.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aiGj.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aPiQ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aFRs.jpg",
                "https://s1.ax1x.com/2018/08/21/P4akzn.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aEMq.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aVs0.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aZLV.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aQJJ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4andU.jpg",
                "https://s1.ax1x.com/2018/08/21/P4auoF.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aDSA.jpg",
                "https://s1.ax1x.com/2018/08/21/P4adFe.jpg",
                "https://s1.ax1x.com/2018/08/21/P4alW9.jpg",
                "https://s1.ax1x.com/2018/08/21/P4a1zR.jpg",
                "https://s1.ax1x.com/2018/08/21/P4a8Q1.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aGsx.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aJL6.jpg",
                "https://s1.ax1x.com/2018/08/21/P4ateK.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aNdO.jpg",
                "https://s1.ax1x.com/2018/08/21/P4aUoD.jpg",
                "https://s1.ax1x.com/2018/08/21/P4a0Wd.jpg",
                "https://s1.ax1x.com/2018/08/21/P4awJH.jpg",
                "https://s1.ax1x.com/2018/08/21/P4B734.jpg",
                "https://s1.ax1x.com/2018/08/21/P4BI4U.jpg",
                "https://s1.ax1x.com/2018/08/21/P4B5NT.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DpCD.jpg",
                "https://s1.ax1x.com/2018/08/21/P4BTCF.jpg",
                "https://s1.ax1x.com/2018/08/21/P4BHgJ.jpg",
                "https://s1.ax1x.com/2018/08/21/P4D98e.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Bbv9.jpg",
                "https://s1.ax1x.com/2018/08/21/P4BLuR.jpg",
                "https://s1.ax1x.com/2018/08/21/P4BOD1.jpg",
                "https://s1.ax1x.com/2018/08/21/P4BXHx.jpg",
                "https://s1.ax1x.com/2018/08/21/P4BvE6.jpg",
                "https://s1.ax1x.com/2018/08/21/P4BxUK.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Bz4O.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DCgH.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DAbt.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DPvd.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DnPS.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DFKA.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DkDI.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DVVP.jpg",
                "https://s1.ax1x.com/2018/08/21/P4Du8g.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DZUf.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DK2Q.jpg",
                "https://s1.ax1x.com/2018/08/21/P4De58.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DGV0.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DMvj.jpg",
                "https://s1.ax1x.com/2018/08/21/P4DlKs.jpg",
                "https://s1.ax1x.com/2018/08/21/P4D3bq.jpg",
                "https://s1.ax1x.com/2018/08/21/P4D1rn.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UcfH.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UalR.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UUp9.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UD0K.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UtfJ.jpg",
                "https://s1.ax1x.com/2018/08/21/P5Ud61.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UwOx.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UBm6.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UrTO.jpg",
                "https://s1.ax1x.com/2018/08/21/P5U6te.jpg",
                "https://s1.ax1x.com/2018/08/21/P5UykD.jpg"
        };
    }
}