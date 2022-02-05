# Collection

---

### Collections??

컬렉션이란 자주 사용하는 기초적인 자료구조를 모아 놓은 일종의 프레임워크로 표준 라이브러리로 제공하고 있다. 컬렉션의 종류는 List, Set, Map이 있으며 자바와는 다르게 불변형과 갸변형으로 나뉜다. 자바에서는 가변형 컬렉션만 취급되므로 자바와 상호작용하는 코드에는 주의가 필요하다.

| 컬렉션 | 불변형 | 가변형                                            |
| :----- | ------ | ------------------------------------------------- |
| List   | listOf | mutableListOf, arrayListOf                        |
| Set    | setOf  | mutableSetOf, hashSetOf, linkedSetOf, sortedSetOf |
| Map    | mapOf  | mutableMapOf, hashMapOf, linkedMapOf, sortedMapOf |

---

### Collection 인터페이스의 멤버

| 멤버                                  | 설명                                      |
| ------------------------------------- | ----------------------------------------- |
| size                                  | 컬렉션의 크기를 나타낸다                  |
| isEmpty()                             | 컬렉션이 비어 있으면 true 반환            |
| contains(element : E)                 | 특정 요소가 있다면 true 반환              |
| containsAll(elements : Collection<E>) | 인자로 받아들인 컬렉션이 있다면 true 반환 |

### MutableCollection 인터페이스의 멤버 메서드

| 멤버 메서드                          | 설명                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| add(element : E)                     | 인자로 전달 받은 요소를 추가하고 true 반환,<br />이미 요소가 있거나 중복이 허용되지 않으면 false |
| remove(element : E)                  | 인자로 전달 받은 요소를 삭제하고 true 반환,<br />삭제하려는 요소가 없다면 false 반환 |
| addAll(elements : Collection<E>)     | 컬렉션을 인자로 전달 받아 모든 요소를 추가하고 true 반환<br />실패하면 false 반환 |
| removeAll(elements : Collection<E>)  | 컬렉션을 인자로 전달 받아 모든 요소를 삭제하고 true 반환<br />실패하면 false 반환 |
| retainAll(elements : Collections<E>) | 인자로 전달 받은 컬렉션의 요소만 보유한다. 성공하면 true 반환<br />실패하면 false 반환 |
| clear()                              | 컬렉션의 모든 요소를 삭제한다.                               |

---

### List와 배열의 차이

List는 Array<T>와 사용 방법이 비슷하다. 하지만 Array클래스에 의해 생성되는 배열 객체는 내부 구조상 고정된 크기의 메모리를 가지고 있다. 또한 List<T>,MutableList<T>는 인터페이스로 설계되어있고 이것을 하위에서 특정한 자료구조로 구현한다. 따라서 해당 자료구조에 따라 성능이 달라진다. ex) LinkedList, ArrayList

---

### Set

set은 정해진 순서가 없는 요소들의 집합을 나타내는 컬렉션이다. Set의 경우 집합의 개념이라 중복을 가질 수 없다. 다시 말해 모든 요소가 유일해야 한다.

#### hashSetOf

헬퍼 함수 hashSetOf()를 통해 해시 테이블에 요소를 저장할 수 있는 자바의 HashSet 컬렉션을 만든다. 해시 테이블이란 내부적으로 키와 인덱스를 이용해 검색과 변경 등을 매우 빠르게 처리 할 수 있는 자료구조이다. hashSetOf()는 불변성 선언이 없기 때문에 추가 및 삭제등의 기능이 가능하다.

### sortedSetOf

sortedSetOf()는 자바의 TreeSet 컬렉션을 정렬된 상태로 반환한다. java.util.* 패키지를 임포트 해야한다. TreeSet은 저장된 데이터의 값에 따라 정렬 되며 일종의 개선된 이진 탐색 트리인 레드 블랙 트리 알고리즘을 사용해 자료구조를 구성한다. HashSet 보다 성능이 좀 떨어지고 데이터를 추가하거나 삭제하는 데 시간이 걸리지만 정렬이 뛰어나다는 장점을 가지고 있다.

### Map

Map은 요소가 키와 값의 쌍 형태로 저장이 되며 키는 중복 될 수 없고 유일해야한다. 하지만 값은 중복해서 사용가능하다. 동일한 키로 값이 새로 들어오면 기존의 값은 사라지게 된다.

---

## 컬렉션의 확장 함수

코틀린은 컬렉션의 많은 확장 함수를 제공한다. 기준은 다음과 같다.

- 연산자(Operator) 기능의 메서드 : 더하고 빼는 등의 기능
- 집계(Aggregator) 기능의 메서드 : 최대, 최소, 집합, 총합 등의 계산 기능
- 검사(Check) 기능의 메서드 : 요소를 검사하고 순환하는 기능
- 필터(Filtering) 기능의 메서드 : 원하는 요소를 골라내는기능
- 변환(Transformer) 기능의 메서드 : 뒤집기, 정렬, 자르기 등의 변환 기능

---

### 컬렉션의 연산

연산자를 사용하여 컬렉션에 대해 더하거나 빼는 등의 기능을 수행할 수있다. 일반적인 연산자인 +, - 를 사용하여 컬렉션 요소를 하나씩 더하거나 뺄 수 있고 컬렉션 자체를 더하거나 뺄 수 있다.

~~~kotlin
println(list1 + "four")
println(list1 - "four")
println(list1 - list2)
~~~

---

### 요소의 처리와 집계

요소를 집계하는 확장 함수는 forEach, forEachIndexed, onEach, max, min, maxBy, minBy, fold, reduce, sumBy() 등이 있다.

- forEach : 각 요소를 람다식으로 처리한 이후 컬렉션을 반환하지 않는다. onEach를 사용하면 각 요소를 람다식으로 처리하고 각 컬렉션을 반환 받을 수 있다.
- forEachIndexed : 각 요소를 index, value로 나누어 람다식으로 처리할 수있다. forEach처럼 켈력션을 반환하지 않는다.
- count : 요소의 개수 반환
- max : 최대값 반환
- min : 최소값 반환
- maxBy : 람다식에 의해 컬렉션의 요소를 처리하면 키를 기준으로 최대값 반환
- minBy : 람다식에 의해 컬렉션의 요소를 처리하면 키를 기준으로 최소값 반환
- fold : 초기값과 정해진 식에 따라 처음 요소 부터 끝요소에 적용하며 값 생성, foldRight는 fold와 반대로 끝요소부터 적용
- reduce : fold와 동일하지만 초기값을 사용하지 않는다. reduceRight 마찬가지로 끝요소 부터 적용된다.

----

### 요소의 검사

- all : 람다식에서 모든 요소가 일치할 때 true를 반환
- any : 람다식에서 최소한 하나 혹은 그 이상의 특정 요소가 일치하면 true 반환
- contains : 특정 요소가 포함되어 있느면 true 반환, 범위 연산자 in을 사용하여 요소의 포함 여부를 알 수도 있으며, 모든 요소가 포함되어 있는지 검사하려면 containsAll()을 사용하면 된다.
- none : 컬렉션에 요소가 없으면 true, 그렇지 않으면 false를 반환한다.
- isEmpty : 컬렉션이 비어있으면 true 아니면 false를 반환하며 isNotEmpty는 반대로 반환한다.

---

### 요소의 필터와 추출

- filter : 주어진 컬렉션을 it으로 받아 조건에 맞는 경우만 골라 낼 수있다. filterNot은 반대로 조건에 맞지 않는 경우만 골라 낸다. filterNotNull같은 경우 null을 제외한 나머지를 받는다.
- filterIndexed : 해당 메서드를 사용하면 2개의 인자를 람다식에서 받아 각각 인덱스와 값에 대한 특정 수식에 맞는 조건을 골라낼 수 있다.
- filterIndexTo : filterIndexed에 컬렉션으로 반환되는 기능이 추가 되어 있다.
- filterKeys : 요소를 it으로 받아 키에 대한 조건을 맞는 부분을 반환한다.
- filterValues : 요소를 it으로 받아 값에 대한 조건이 맞으면 반환한다.
- filterIsInstance<T> : 원하는 자료형만 골라내 추출할 수 있다.
- slice : 특정 범위의 인덱스를 가진 List를 인자로 사용해 기존 List에서 요소들을 잘라낼 수 있다.
- take : n개의 요소를 가진 List를 반환한다. ex) list.take(2) : 앞에 두개 요소 반환 , list.takeLast(2) : 마지막 두 요소 반환, takeWhile{ it < 3 } 조건식에 따른 반환
- drop : take와 정반대로 처음부터 n개의 요소를 제외하고 List를 반환한다. ex) list.drop(3) 앞에 3개 빼고 반환, list.dropWhile{ it < 3 } : 3미만을 제외하고 반환
- distinct : 여러 중복 요소가 있는 경우 1개로 취급해 다시 컬랙션 List로 반환 -> 합집합
- intersect : 겹치는 요소만 골라네 List 반환 -> 교집합

