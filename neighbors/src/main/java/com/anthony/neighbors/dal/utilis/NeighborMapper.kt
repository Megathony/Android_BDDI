package com.anthony.neighbors.dal.utilis

import com.anthony.neighbors.dal.room.entities.NeighborEntity
import com.anthony.neighbors.models.Neighbor

fun NeighborEntity.toNeighbor() = Neighbor(
    id = id.toInt(),
    name = name,
    avatarUrl = avatarUrl,
    address = address,
    phoneNumber = phoneNumber,
    aboutMe = aboutMe,
    favorite = favorite,
    webSite = webSite ?: ""
)

fun Neighbor.toEntity() = NeighborEntity(
    id = id.toLong(),
    name = name,
    avatarUrl = avatarUrl,
    address = address,
    phoneNumber = phoneNumber,
    aboutMe = aboutMe,
    favorite = favorite,
    webSite = webSite
)
