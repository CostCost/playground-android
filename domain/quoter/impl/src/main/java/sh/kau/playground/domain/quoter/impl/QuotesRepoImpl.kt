package sh.kau.playground.domain.quoter.impl

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import me.tatarka.inject.annotations.Inject
import sh.kau.playground.common.networking.NetworkApi
import sh.kau.playground.domain.quoter.api.Quote
import sh.kau.playground.domain.quoter.api.QuotesRepo
import sh.kau.playground.domain.shared.di.AppScope

@AppScope
@Inject
class QuotesRepoImpl(
    private val api: NetworkApi,
) : QuotesRepo {

  override suspend fun quoteForTheDay(): Quote {
    return fetchQuote()
  }

  private suspend fun fetchQuote(): Quote {
    val response: HttpResponse =
        api.client().get("https://zenquotes.io/api/today") {
          contentType(ContentType.Application.Json)
        }
    return response.body<List<Quote>>()[0]
  }
}
