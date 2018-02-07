package com.xapo.demo.rodion.githubxapodemo.model

/**
 * Holds GitHub repository info.
 */
data class Hub(
        val id: Int,
        val name: String,
        val full_name: String,
        val description: String,
        val html_url: String,
        val owner: Owner
)