package generic;

//public interface WebBean<W extends WebBean<W, C>, C extends CoreBean> {
public interface WebBean<W extends WebBean<W, C>, C extends CoreBean> {

	
	public C asCoreBean(W bean);
	public W asWebBean(C bean);
}
