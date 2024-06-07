package com.yudhi.moviedatabase.presentation.viewmodel

/*
class MovieViewModelFactory(val remoteDataSource: RemoteDataSource, val pref: MyDataStore) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MovieViewModelFactory? = null

        fun getInstance(context: Context): MovieViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MovieViewModelFactory(
                    RemoteDataSource(ApiClient.instance),
                    MyDataStore
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieRepository(remoteDataSource), pref) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
 */