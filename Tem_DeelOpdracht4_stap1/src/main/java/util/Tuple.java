package util;

public class Tuple<T> {

	private final T t1;
	private final T t2;

	public Tuple(T t1, T t2) throws InstantiationException {
		if(t1 == null || t2 == null){
			throw new InstantiationException();
		}
		this.t1 = t1;
		this.t2 = t2;
	}

	public T getLeft(){
		return t1;
	}

	public T getRight(){
		return t2;
	}

	public boolean contains(T t){
		return t1.equals(t) || t2.equals(t);
	}

	public T getOther(T t){
		if(t1.equals(t)){
			return t2;
		}else{
			return t1;
		}
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Tuple<?>){
			try{
				return equals((Tuple<T>) o);
			}catch (ClassCastException e){
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public int hashCode(){
		return t1.hashCode() + t2.hashCode();
	}

	public boolean equals(Tuple<T> t){
		return (getLeft().equals(t.getLeft()) && getRight().equals(t.getRight())) || (getLeft().equals(t.getRight()) && getRight().equals(t.getLeft()));
	}

}
