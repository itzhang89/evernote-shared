package example.springcode.chap03.lookup;

public abstract class GetBeanTest {
  public void showMe(){
    this.getBean().shouMe();
  }

  public abstract User getBean();
}
