import dao.DataFetcherDao
import gui.BodyFooter
import gui.BodyHeader
import gui.BodyMain
import me.tatarka.inject.annotations.Component
import service.RandomNumberService

@AppScope
@Component
abstract class AppComponent {
    abstract val randomNumberService: RandomNumberService
    abstract val dataFetcherDao: DataFetcherDao
    abstract val bodyHeader: BodyHeader
    abstract val bodyMain: BodyMain
    abstract val bodyFooter: BodyFooter
}