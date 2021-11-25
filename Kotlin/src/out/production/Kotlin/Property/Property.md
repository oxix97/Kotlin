# Kotlin_6

### 목차

- [Property? field?]()
- [Java에서의 getter, setter]()
- [Kotlin에서의 getter, setter]()
- [getter, setter 커스텀하기]()
- [lateinit_지연초기화]()
- [lazy_지연초기화]()
- [by를 이용한 위임]()
- [companion]()
- [static]()
- [object]()

---

### Property? field?

> 객체지향 언어인 자바에서 객체는 고유한 속성(특징)을 가지는데,
> 그 속성을 칭하는 단어를 프로퍼티(property)라고 한다.그리고 이 속성의 진짜 모습,
> 즉 '실체'를 담는 곳이 필드(field, 멤버변수)입니다.

---

### Java에서의 접근 방식

~~~java
class Person {

    private String name;
    private int age;

    // 생성자
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 게터/세터
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Kildong", 30);
        // p1.name = "Dooly"  접근 불가
        p1.setName("Dooly");
        System.out.println(p1.getName());
    }
}
~~~

자바에서는 게터와 세터에 해당하는 접근 메서드를 직접 만들어야 하며 코드가 매우 길어지고 읽기 어렵게 됩니다.

---

### Kotlin에서의 접근 방식

자바와는 다르게 코틀린은 각 프로퍼티에 게터와 세터가 자동으로 만들어 집니다. 위와 같은 코드는 한줄로도 표현이 가능합니다.

~~~kotlin
class Person(var name: String, var age: Int)
~~~

여기서 var는 가변형 프로퍼티, val 은 불변형 프로퍼티 입니다.

~~~kotlin
class User(val id: Int, var name: String, var age: Int)

fun main() {
    val user = User(1, "Sean", 30)
    val name = user.name // 게터에 의한 값 획득
    user.age = 41 // 세터에 의한 값 지정
    user.id = 2 //읽기 전용 프로퍼티에는 setter로 다시 지정 불가
    println("name: $name, ${user.age}")
}
~~~

---

### getter, setter 커스텀하기

~~~kotlin
class User(_id: Int, _name: String, _age: Int) {
    val id: Int = _id
        get() = field

    var name: String = _name
        get() = field
        // 해당 setter를 커스텀 할 수도 있다.
        set(value) {
            field = value.uppercase()
        }

    var age: Int = _age
        get() = field
        set(value) {
            field = value
        }
}

fun main() {
    val user = User(1, "Sean", 30)
    val name = user.name // 게터에 의한 값 획득
    user.age = 41 // 세터에 의한 값 지정
    user.id = 2 //읽기 전용 프로퍼티에는 setter로 다시 지정 불가
    println("name: $name, ${user.age}")
}

~~~

### lateinit

> 기본 자료형들은 생성자에서 반드시 초기화 되어야하지만 의존성이 있는 경우 완전하게 초기화하기가 어렵습니다.
> 또한 즉시 사용하지 않는 경우 미리 생성해서 초기화하면 메모리가 낭비가 되는 등 여러므로 불편합니다.
> 이러한 경우 지연초기화를 사용합니다.

#### 프로퍼티 지연초기화하기

~~~kotlin
class Person {
    lateinit var name: String

    fun test() {
        // :: -> 참조할 떄 사용
        //!Person.getClass().isInitialized()
        if(!::name.isInitialized) { // 프로퍼티의 초기화 여부 판단
            println("not initialized")
        } else {
            println("initialized")
        }
    }
}

fun main() {
    val kildong = Person()
    kildong.test()
    kildong.name = "Kildong" // 이 시점에서 초기화가 된다. (지연초기화)
    kildong.test()
    println("name = ${kildong.name}")
}
~~~

#### 실행 결과

> not initialized <br>
> initialized <br>
> name = Kildong

#### lateinit의 제한

- var로 선언된 프로퍼티만 가능
- 프로퍼티에 대한 게터와 세터를 사용할 수 없다.

---
###  lazy

> lateinit을 통해 객체를 선언하는 경우 var를 사용했지만 var은 가변형이기 때문에 언제든 변경이 가능합니다.
> val를 사용하여 읽기 전용 객체나 프로퍼티를 나중에 초기화하려면 lazy을 사용해야 합니다.

~~~kotlin
class LazyTest {
    init {
        println("init block") // (2) 초기화 블록
    }

    val subject by lazy {
        println("lazy initialized") // (6)
        "Kotlin Programming" // (7) lazy 반환값
    }
    fun flow() {
        println("not initialized") // (4)
        println("subject one: $subject") // (5) 최초 초기화 시점!
        println("subject two: $subject") // (8) 이미 초기화된 값 사용
    }
}

fun main() {
    val test = LazyTest() // (1) 객체 생성
    test.flow() // (3)
}
~~~

#### 실행결과

> init block <br>
> not initialized <br>
> lazy initialized <br>
> subject one : Kotlin Programming <br>
> subject two : Kotlin Programming

5번에서 subject값이 lazy 블록에 있는 6번을 실행시킨 이후 반환이 되어 초기화가 됩니다.

> subject one : Kotlin Programming -> 최초 초기화 시점 <br>
> subject one : Kotlin Programming -> 이미 초기화 된 값 사용

#### lazy 제한

- 호출 시점에 lazy정의에 의해 블록 부분 초기화 진행
- val에서만 사용가능 
- val 이기 때문에 값 변경 불가

---
### by를 이용한 위임

코틀린에서 특정 클래스를 확장하거나 이용할 수 있도록 by를 통한 위임이 가능합니다. 
by를 사용한 경우 위임된 클래스가 가지는 멤버를 참조변수 없이 호출할 수 있게 됩니다.

~~~kotlin
interface Animal {
    fun eat() {}
}

class Cat : Animal {}

val cat = Cat()

class Robot : Animal by cat  // Animal의 정의된 Cat의 모든 멤버를 Robot에 위임
~~~

#### 위임을 사용하는 이유?

코틀린이 가지고 있는 표준 라이브러리는 open으로 정의되지 않는 클래스를 사용한다 -> final 형태의 클래스가 default 값이므로
상속이나 직접 클래스의 확장이 어렵게 되어 무분별한 상속을 방지할 수 있습니다. 필요한 경우에만 위임을 통해 상속에 따른
복잡한 문제를 해결함과 동시에 기능을 추가 확장 구현할 수 있습니다.

~~~kotlin
interface Car {
    fun go(): String
}

class VanImpl(val power: String): Car {
    override fun go() = "는 짐을 적재하며 $power 마력을 가집니다."
}

class SportImpl(val power: String): Car {
    override fun go() = "는 경주용에 사용되며 $power 마력을 가집니다."
}

class CarModel(val model: String, impl: Car): Car by impl {
    fun carInfo() {
        println("$model ${go()}") // 참조 없이 각 인터페이스 구현 클래스의 go를 접근
    }
}

fun main() {
    val myDamas = CarModel("Damas 2010", VanImpl("100마력"))
    val my350z = CarModel("350Z 2008", SportImpl("350마력"))

    myDamas.carInfo()
    my350z.carInfo()
}
~~~


