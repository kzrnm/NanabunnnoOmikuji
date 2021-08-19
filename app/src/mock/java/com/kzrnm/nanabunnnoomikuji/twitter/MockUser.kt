package com.kzrnm.nanabunnnoomikuji.twitter

import twitter4j.RateLimitStatus
import twitter4j.Status
import twitter4j.URLEntity
import twitter4j.User
import java.util.*

data class MockUser(private val screenName: String) : User {
    override fun getScreenName(): String {
        return screenName
    }

    override fun compareTo(other: User?): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getRateLimitStatus(): RateLimitStatus {
        throw NotImplementedError("Not implemented")
    }

    override fun getAccessLevel(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getId(): Long {
        throw NotImplementedError("Not implemented")
    }

    override fun getName(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getEmail(): String {
        throw NotImplementedError("Not implemented")
    }


    override fun getLocation(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getDescription(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun isContributorsEnabled(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileImageURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getBiggerProfileImageURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getMiniProfileImageURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getOriginalProfileImageURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun get400x400ProfileImageURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileImageURLHttps(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getBiggerProfileImageURLHttps(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getMiniProfileImageURLHttps(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getOriginalProfileImageURLHttps(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun get400x400ProfileImageURLHttps(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun isDefaultProfileImage(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun isProtected(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getFollowersCount(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getStatus(): Status {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBackgroundColor(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileTextColor(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileLinkColor(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileSidebarFillColor(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileSidebarBorderColor(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun isProfileUseBackgroundImage(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun isDefaultProfile(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun isShowAllInlineMedia(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getFriendsCount(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getCreatedAt(): Date {
        throw NotImplementedError("Not implemented")
    }

    override fun getFavouritesCount(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getUtcOffset(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getTimeZone(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBackgroundImageURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBackgroundImageUrlHttps(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBannerURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBannerRetinaURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBannerIPadURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBannerIPadRetinaURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBannerMobileURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBannerMobileRetinaURL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBanner300x100URL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBanner600x200URL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getProfileBanner1500x500URL(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun isProfileBackgroundTiled(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getLang(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getStatusesCount(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun isGeoEnabled(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun isVerified(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun isTranslator(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getListedCount(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun isFollowRequestSent(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getDescriptionURLEntities(): Array<URLEntity> {
        throw NotImplementedError("Not implemented")
    }

    override fun getURLEntity(): URLEntity {
        throw NotImplementedError("Not implemented")
    }

    override fun getWithheldInCountries(): Array<String> {
        throw NotImplementedError("Not implemented")
    }
}
