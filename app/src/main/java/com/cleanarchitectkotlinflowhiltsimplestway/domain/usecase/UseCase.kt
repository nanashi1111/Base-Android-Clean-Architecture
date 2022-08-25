package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import kotlinx.coroutines.flow.Flow


abstract class UseCase<Output, Params>() {
    abstract  operator fun invoke(param: Params): Flow<State<Output>>
}