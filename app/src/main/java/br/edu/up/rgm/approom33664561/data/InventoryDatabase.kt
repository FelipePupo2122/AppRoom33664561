package br.edu.up.rgm.approom33664561.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    /**
                     * Definir esta opção no construtor de banco de dados do seu aplicativo significa que o Room
                     * exclui permanentemente todos os dados das tabelas do seu banco de dados quando
                     * tenta realizar uma migração sem caminho de migração definido.
                     */
                        //analisada essa opção de um estudo feito via video no youtube
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}