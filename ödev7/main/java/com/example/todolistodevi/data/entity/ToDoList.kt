package com.example.todolistodevi.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "ToDoList")
data class ToDoList(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "id")@NotNull var id: Int,
                    @ColumnInfo(name = "adi")@NotNull var adi: String,
                    @ColumnInfo(name = "aciklama") var aciklama: String?
): Serializable {
}