import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import kotlinx.coroutines.runBlocking
import org.ufoss.kotysa.postgresql.PostgresqlTable
import org.ufoss.kotysa.r2dbc.sqlClient
import org.ufoss.kotysa.tables

data class User(val name: String, val age: Int)

object Users : PostgresqlTable<User>("users") {
    val name = text(User::name)
    val age = integer(User::age)

    init {
        primaryKey(name, age)
    }
}

fun main() {
    val client = PostgresqlConnectionFactory(
        PostgresqlConnectionConfiguration.builder()
            .host("localhost")
            .port(5440)
            .username("postgres")
            .password("1")
            .database("kotysa")
            .build()
    ).sqlClient(tables().postgresql(Users))
    runBlocking {
        (client select Users.name and Users.age from Users).fetchAll().collect {
            println(it)
        }
    }
}
