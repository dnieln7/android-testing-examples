package com.dnieln7.testing.datasource.spacex.mission

import com.dnieln7.testing.model.spacex.Mission
import kotlinx.coroutines.delay
import retrofit2.Response

class FakeMissionRemoteDataSource : MissionRemoteDataSource {

    override suspend fun getMissions(): Response<List<Mission>> {
        val missions = listOf(
            Mission(
                missionId = "4aa8a2c2-8a5c-4a8f-bc21-10a2ebc12105",
                description = "First mission",
                missionName = "Test 1",
                twitter = "n/a",
                website = "n/a",
                wikipedia = "n/a"
            ),
            Mission(
                missionId = "c79d48fb-fd8f-4806-a7e7-e754039a7c33   ",
                description = "Second mission",
                missionName = "Test 2",
                twitter = "n/a",
                website = "n/a",
                wikipedia = "n/a"
            ),
            Mission(
                missionId = "4d2008b9-147e-47b1-a1dc-7445a705d7e5",
                description = "Third mission",
                missionName = "Test 3",
                twitter = "n/a",
                website = "n/a",
                wikipedia = "n/a"
            ),
        )

        return Response.success(missions)
    }
}