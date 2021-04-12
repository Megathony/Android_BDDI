import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.anthony.neighbors.repositories.service.DUMMY_NeighborS
import java.util.concurrent.Executors

@Database(
    entities = [NeighborEntity::class],
    version = 1
)
abstract class NeighborDataBase : RoomDatabase() {
    abstract fun neighborDao(): NeighborDao

    companion object {
        private var instance: NeighborDataBase? = null
        fun getDataBase(context: Context): NeighborDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    NeighborDataBase::class.java,
                    "neighbor_database.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    fun getDataBase(application: Application): NeighborDataBase {
        if (instance == null) {
            instance = Room.databaseBuilder(
                application.applicationContext,
                NeighborDataBase::class.java,
                "neighbor_database.db"
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    insertFakeData()
                }
            })
                .fallbackToDestructiveMigration()
                .build()
        }
        return instance!!
    }

    private fun insertFakeData() {
        Executors.newSingleThreadExecutor().execute {
            DUMMY_NeighborS.forEach {
                instance?.neighborDao()?.add(it.toEntity())
            }
        }
    }
}
