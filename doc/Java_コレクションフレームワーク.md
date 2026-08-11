## コレクションの種類と特徴
### コレクションフレームワークとは
- Javaのコレクションフレームワークは、データの集合を効率的に扱うためのクラスとインターフェースのセットである
- コレクションフレームワークは、データの格納、検索、削除、並べ替えなどの操作を簡単に行うための標準的な方法を提供する
### コレクションの種類
- コレクションフレームワークには、主に以下の種類がある
  - List: 順序付きの要素の集合で、重複を許す。要素の追加、削除、検索が可能。例: ArrayList, LinkedList
  - Set: 順序なしの要素の集合で、重�複を許さない。要素の追加、削除、検索が可能。例: HashSet, TreeSet
  - Map: キーと値のペアの集合で、キーは重複を許さない。値の追加、削除、検索が可能。例: HashMap, TreeMap
  - Queue: 先入れ先出しの要素の集合で、順序付き。要素の追加、削除、検索が可能。例: LinkedList, PriorityQueue
  - Deque: 両端キューで、先入れ先出しと後入れ先出しの�要素の集合。要素の追加、削除、検索が可能。例: ArrayDeque, LinkedList
### コレクションの特徴
- コレクションフレームワークの特徴は以下の通りである
  - データの格納、検索、削除、並べ替えなどの操作を簡単に行える
  - データの型を指定することができ、型安全なコレクションを作成できる
  - データの格納順序を保持することができる（ListやQueueなど）
  - データの重複を許すかどうかを指定できる（SetやMapなど）

## ArrayListクラス
- Listインタフェースを実装したクラスで、可変長の配列として動作する
- 配列と違い、要素数が可変（要素の追加、削除が可能）
- 管理対象が参照型のみ（配列は基本データ型も管理可能）
### 主なメソッド
- add(E e): 要素を追加する
- remove(int index): 指定した位置の要素を削除する.削除した場合は削除した要素を返す
- get(int index): 指定した位置の要素を取得する
- size(): 要素数を取得する
- clear(): 全ての要素を削除する
- remove(Object o): 指定したオブジェクトを削除する.削除した場合はtrueを返す
- contains(Object o): 指定したオブジェクトが含まれているかを確認する
- indexOf(Object o): 指定したオブジェクトの最初の位置を返す（存在しない場合は-1を返す）
- lastIndexOf(Object o): 指定したオブジェクトの最後の位置を返す（存在しない場合は-1を返す）
- toArray(): コレクションの要素を配列に変換する
- isEmpty(): コレクションが空かどうかを確認する
- set(int index, E element): 指定した位置の要素を置き換える
- subList(int fromIndex, int toIndex): 指定した範囲の部分リストを返す
- sort(Comparator<? super E> c): 指定した比較器を使用してリストをソートする
- forEach(Consumer<? super E> action): 各要素に対して指定したアクションを実行する
- stream(): コレクションの要素をストリームとして取得
### ArrayListのコンストラクタ
- ArrayList(): 空のArrayListを生成する
- ArrayList(int initialCapacity): 指定した初期容量を持つArrayListを生成する
- ArrayList(Collection<? extends E> c): 指定したコレクションの要素を含むArrayListを生成する
## HashSetクラス
- Setインタフェースを実装したクラスで、重複を許さない要素の集合を表す
- 要素の順序は保証されない.
- 同じ要素は1つしか保持されない（重複を許さない）
- HashSetは内部的にハッシュテーブルを使用して要素を管理するため、要素の追加、削除、検索が高速である
### 主なメソッド
- add(E e): 要素を追加する.追加した場合はtrueを返す
- remove(Object o): 指定したオブジェクトを削除する.削除した場合はtrueを返す
- contains(Object o): 指定したオブジェクトが含まれているかを確認する
- size(): 要素数を取得する
- clear(): 全ての要素を削除する
- isEmpty(): コレクションが空かどうかを確認する
- iterator(): コレクションの要素を反復処理するためのイテレータを返す
- toArray(): コレクションの要素を配列に変換する
- forEach(Consumer<? super E> action): 各要素に対して指定したアクションを実行する
- stream(): コレクションの要素をストリームとして取得
### HashSetのコンストラクタ
- HashSet(): 空のHashSetを生成する
- HashSet(Collection<? extends E> c): 指定したコレクションの要素を含むHashSetを生成する
- HashSet(int initialCapacity): 指定した初期容量を持つHashSetを生成する
- HashSet(int initialCapacity, float loadFactor): 指定した初期容量と負荷率を持つHashSetを生成する