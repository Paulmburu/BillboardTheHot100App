package tk.paulmburu.billboardthehot_100app.di

import dagger.Component
import tk.paulmburu.billboardthehot_100app.presentations.MainActivity

@Component
interface MusicComponents {
    fun inject(mainActivity: MainActivity)
}