package ua.knu.maksym_pashchenko.todolite.data.local.mappers

import ua.knu.maksym_pashchenko.todolite.data.local.entity.TodoEntity
import ua.knu.maksym_pashchenko.todolite.domain.models.TodoItem

fun TodoEntity.toDomain(): TodoItem = TodoItem(
    id = id,
    title = title,
    isDone = isDone
)

fun TodoItem.toEntity(): TodoEntity = TodoEntity(
    id = id,
    title = title,
    isDone = isDone
)