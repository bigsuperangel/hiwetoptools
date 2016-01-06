package com.fj.hiwetop.tools.collection;

/**
 * 元组调用，返回不定个数的结果集
 */
public class TupleUtil {

	public static <A,B> TwoTuple<A,B> tuple(A a, B b){
		return new TupleUtil().new TwoTuple<A,B>(a,b);
	}

	public static <A,B,C> ThreeTuple<A,B,C> tuple(A a, B b,C c){
		return new TupleUtil().new ThreeTuple<A,B,C>(a,b,c);
	}

	public static <A,B,C,D> FourTuple<A, B,C,D>  tuple(A a ,B b,C c,D d){
		return new TupleUtil().new FourTuple<A, B,C,D>( a,b,c,d);
	}

	public static <A, B,C,D,E> FiveTuple<A, B,C,D,E> tuple(A a, B b,C c,D d,E e){
		return new TupleUtil().new FiveTuple<A, B,C,D,E>(a,b,c,d,e);
	}


	class TwoTuple<A, B> {
		public final A a;
		public final B b;
		public TwoTuple(A a ,B b) {
			this.a = a;
			this.b = b;
		}
	}

	class ThreeTuple<A, B,C> {
		public final A a;
		public final B b;
		public final C c;
		public ThreeTuple(A a ,B b,C c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	class FourTuple<A, B,C,D> {
		public final A a;
		public final B b;
		public final C c;
		public final D d;
		public FourTuple(A a ,B b,C c,D d) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}
	}

	class FiveTuple<A, B,C,D,E> {
		public final A a;
		public final B b;
		public final C c;
		public final D d;
		public final E e;
		public FiveTuple(A a ,B b,C c,D d,E e) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.e = e;
		}
	}
}
