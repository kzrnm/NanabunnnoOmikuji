package com.kzrnm.nanabunnnoomikuji.twitter

import twitter4j.*
import java.util.*


data class MockStatus(
    private val id: Long,
    private val createdAt: Date,
    private val user: User,
) : Status {
    override fun getCreatedAt(): Date {
        return createdAt
    }

    override fun getUser(): User {
        return user
    }

    override fun getId(): Long {
        return id
    }

    override fun compareTo(other: Status?): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getRateLimitStatus(): RateLimitStatus {
        throw NotImplementedError("Not implemented")
    }

    override fun getAccessLevel(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getUserMentionEntities(): Array<UserMentionEntity> {
        throw NotImplementedError("Not implemented")
    }

    override fun getURLEntities(): Array<URLEntity> {
        throw NotImplementedError("Not implemented")
    }

    override fun getHashtagEntities(): Array<HashtagEntity> {
        throw NotImplementedError("Not implemented")
    }

    override fun getMediaEntities(): Array<MediaEntity> {
        throw NotImplementedError("Not implemented")
    }

    override fun getSymbolEntities(): Array<SymbolEntity> {
        throw NotImplementedError("Not implemented")
    }

    override fun getText(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getDisplayTextRangeStart(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getDisplayTextRangeEnd(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun getSource(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun isTruncated(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getInReplyToStatusId(): Long {
        throw NotImplementedError("Not implemented")
    }

    override fun getInReplyToUserId(): Long {
        throw NotImplementedError("Not implemented")
    }

    override fun getInReplyToScreenName(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getGeoLocation(): GeoLocation {
        throw NotImplementedError("Not implemented")
    }

    override fun getPlace(): Place {
        throw NotImplementedError("Not implemented")
    }

    override fun isFavorited(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun isRetweeted(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getFavoriteCount(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun isRetweet(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getRetweetedStatus(): Status {
        throw NotImplementedError("Not implemented")
    }

    override fun getContributors(): LongArray {
        throw NotImplementedError("Not implemented")
    }

    override fun getRetweetCount(): Int {
        throw NotImplementedError("Not implemented")
    }

    override fun isRetweetedByMe(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getCurrentUserRetweetId(): Long {
        throw NotImplementedError("Not implemented")
    }

    override fun isPossiblySensitive(): Boolean {
        throw NotImplementedError("Not implemented")
    }

    override fun getLang(): String {
        throw NotImplementedError("Not implemented")
    }

    override fun getScopes(): Scopes {
        throw NotImplementedError("Not implemented")
    }

    override fun getWithheldInCountries(): Array<String> {
        throw NotImplementedError("Not implemented")
    }

    override fun getQuotedStatusId(): Long {
        throw NotImplementedError("Not implemented")
    }

    override fun getQuotedStatus(): Status {
        throw NotImplementedError("Not implemented")
    }

    override fun getQuotedStatusPermalink(): URLEntity {
        throw NotImplementedError("Not implemented")
    }

}