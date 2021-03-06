import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        InBean ib = new InBean();
        ib.setTorihikisakiName("セントラル商会");
        ib.setTorihikiDate( LocalDate.of(2018, 5, 2) );
        ib.setGoukei(400);

        ib.getLines().add(  new InLineBean("消しゴム",1,100) );
        ib.getLines().add(  new InLineBean("鉛筆",20,300) );

        OutBean ob = tenki( ib );
        System.out.println( ob.toString() );
    }

    @Override
    public String toString() {
        return "Main []";
    }

    static public OutBean tenki( InBean ib )
    {
        //転記先の伝票を用意する
        OutBean ret = new OutBean();
        //一つしかない項目、日付と取引先と合計金額を書き移す
        //日付を読み取る
        LocalDate torihikiDate       = ib.getTorihikiDate();
        //日付を記入する
        ret.setTorihikiDate(torihikiDate);
        //引先を読み取る
        String torihikisakiName = ib.getTorihikisakiName();
        //取引先を記入する
        ret.setTorihikisakiName(   torihikisakiName );
        //合計金額を読み取る
        int    goukei         = ib.getGoukei();
        //合計金額を記入する
        ret.setGoukei(        goukei );

        //転記先にリストを確保する（確認する）
        List<OutLineBean> outLineList  =ret.getLines();

        //複数ある項目を一つづ書き写す
        //下記をなくなるまで繰り返す
        for( InLineBean il : ib.getLines() )
        {
            //一個目の行について行う
            //転記先の行を確保する（確認する）
            OutLineBean ol = new OutLineBean();
            ret.getLines().add( ol );

            //商品名を読みとる
            String ShouhinName = il.getShouhinName();
            //商品名を記入する
            ol.setShouhinName( ShouhinName );
            //数量を読み取る
            int suuryo = il.getSuuryo();
            //数量を記入する
            ol.setSuuryo(suuryo);
            //金額を読み取る
            int kingaku = il.getKingaku();
            //金額を記入する
            ol.setKingaku(kingaku);
            }
        return ret;
    }

    static OutBean tenkiNoma( InBean ib )
    {
        //野間版ロジック
        //    1.日付の記入
        //   　・転記対象を確認
        LocalDate date = ib.getTorihikiDate();
        //   　・転記先の確認
        OutBean ob = new OutBean();
        //   　・記入する
        ob.setTorihikiDate(date);

        //   2.取引先の記入
        //   　・転記対象を確認
        String torihikisakiName = ib.getTorihikisakiName();
        //   　・転記先の確認
        //   　・記入する
        ob.setTorihikisakiName(torihikisakiName);

        //   3.表の作成
        List<OutLineBean> outList = ob.getLines();
        //   4.表に列の項目を記入
        //   　・転記対象を確認
        //   　・転記先の確認
        //   　・記入する

        List<InLineBean> inList = ib.getLines();
        for( int i =0 ; i < inList.size() ; i++ )
        {
            //   5.商品名を記入
            //   　・転記対象を確認
            String shouhinName = inList.get(i).getShouhinName();
            //   　・転記先の確認
            //   　・記入する
            outList.get(i).setShouhinName(shouhinName);

            //   6.商品名に対応した数量を記入
            //   　・転記対象を確認
            int suuryo = inList.get(i).getSuuryo();

            //   　・転記先の確認
            //   　・記入する
            outList.get(i).setSuuryo( suuryo );

            //   7.商品名に対応した金額の記入
            //   　・転記対象を確認
            int kingaku = inList.get(i).getKingaku();

            //   　・転記先の確認
            //   　・記入する
            outList.get(i).setKingaku( kingaku );

            //   8.5～7を記入対象が終わるまで繰り返す
            //   その際4行を超える場合は次のページへ
        }
        //   9.合計金額の記入
        //   　・転記対象を確認
        int goukei = ib.getGoukei();

        //   　・転記先の確認
        //   　・記入する
        ob.setGoukei( goukei );

        return ob;
    }

    static OutBean tenkiTakata( InBean ib)
    {
        //	 高田版ロジック
        //    	伝票を用意する。
        OutBean ob = new OutBean();
        //    	日付を読む。
        LocalDate date = ib.getTorihikiDate();
        //    	日付を記入する。
        ob.setTorihikiDate(date);
        //    	取引先名を読む。
        String tName = ib.getTorihikisakiName();
        //    	取引先名を記入。
        ob.setTorihikisakiName( tName );

        //    	表の大きさを確認する。
        //    	表の外枠を記入する。
        //    	表の行数を数える
        int size = ib.getLines().size();

        //    	表を行数等分するよう記入する。
        for( int i =0; i < size; i++ )
        {
            OutLineBean olb = new OutLineBean();
            ob.getLines().add( olb );
        }

        //    	表の列数を数える。
        //    	表を列数等分するよう記入する。
        //    	行番号を確認する。
        //    	行番号を記入する。
        //    	列名を読む。
        //    	列名を記入する。
        //
        //    	行番号１の商品名を見る。
        //    	行番号１の商品名を書き写す。
        //    	上記２つの作業を行番号４まで繰り返す。
        for(int i =0 ;i< size; i++)
        {
            String shouhinName = ib.getLines().get(i).getShouhinName();
            ob.getLines().get(i).setShouhinName( shouhinName );
        }

        //    	行番号１の数量を見る。
        //    	行番号１の数量を書き写す。
        //    	上記２つの作業を行番号４まで繰り返す。
        for(int i =0 ;i< size; i++)
        {
            int suuryo = ib.getLines().get(i).getSuuryo();
            ob.getLines().get(i).setSuuryo( suuryo );
        }

        //
        //    	行番号１の金額を見る。
        //    	行番号１の金額を書き写す。
        //    	上記２つの作業を行番号４まで繰り返す。
        int goukei =0;
        for(int i =0 ;i< size; i++)
        {
            int kingaku = ib.getLines().get(i).getKingaku();
            ob.getLines().get(i).setKingaku(kingaku);
            //    	合計金額を計算する。
            goukei += kingaku;
        }

        //    	合計金額を記入する
        ob.setGoukei( goukei );

        return ob;
    }

}
//------------------------------------
class InBean
{
    private LocalDate 			torihikiDate;
    private String	  			torihikisakiName;
    private int		  			goukei;
    private List<InLineBean> 	lines= new ArrayList<InLineBean>();

    public LocalDate getTorihikiDate() {
        return torihikiDate;
    }
    public void setTorihikiDate(LocalDate torihikiDate) {
        this.torihikiDate = torihikiDate;
    }
    public String getTorihikisakiName() {
        return torihikisakiName;
    }
    public void setTorihikisakiName(String torihikisakiName) {
        this.torihikisakiName = torihikisakiName;
    }
    public int getGoukei() {
        return goukei;
    }
    public void setGoukei(int goukei) {
        this.goukei = goukei;
    }
    public List<InLineBean> getLines() {
        return lines;
    }
}

class InLineBean
{
    private String shouhinName;
    private int		suuryo;
    private int		kingaku;



    public InLineBean(String shouhinName, int suuryo, int kingaku) {
        super();
        this.shouhinName = shouhinName;
        this.suuryo = suuryo;
        this.kingaku = kingaku;
    }
    public String getShouhinName() {
        return shouhinName;
    }
    public void setShouhinName(String shouhinName) {
        this.shouhinName = shouhinName;
    }
    public int getSuuryo() {
        return suuryo;
    }
    public void setSuuryo(int suuryo) {
        this.suuryo = suuryo;
    }
    public int getKingaku() {
        return kingaku;
    }
    public void setKingaku(int kingaku) {
        this.kingaku = kingaku;
    }


}
//------------------------------------
class OutBean
{
    private LocalDate 			torihikiDate;
    private String	  			torihikisakiName;
    private int		  			goukei;
    private List<OutLineBean> 	lines= new ArrayList<OutLineBean>();

    public LocalDate getTorihikiDate() {
        return torihikiDate;
    }
    public void setTorihikiDate(LocalDate torihikiDate) {
        this.torihikiDate = torihikiDate;
    }
    public String getTorihikisakiName() {
        return torihikisakiName;
    }
    public void setTorihikisakiName(String torihikisakiName) {
        this.torihikisakiName = torihikisakiName;
    }
    public int getGoukei() {
        return goukei;
    }
    public void setGoukei(int goukei) {
        this.goukei = goukei;
    }
    public List<OutLineBean> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "OutBean [torihikiDate=" + torihikiDate + ", torihikisakiName=" + torihikisakiName + ", goukei=" + goukei
                + ", lines=" + lines + "]";
    }
}

//------------------------------------
class OutLineBean
{
    private String shouhinName;
    private int		suuryo;
    private int		kingaku;

    public String getShouhinName() {
        return shouhinName;
    }
    public void setShouhinName(String shouhinName) {
        this.shouhinName = shouhinName;
    }
    public int getSuuryo() {
        return suuryo;
    }
    public void setSuuryo(int suuryo) {
        this.suuryo = suuryo;
    }
    public int getKingaku() {
        return kingaku;
    }
    public void setKingaku(int kingaku) {
        this.kingaku = kingaku;
    }

    @Override
    public String toString() {
        return "OutLineBean [shouhinName=" + shouhinName + ", suuryo=" + suuryo + ", kingaku=" + kingaku + "]";
    }
}