import dao.DaoConfig
import gui.GuiConfig
import me.tatarka.inject.annotations.Component
import service.ServiceConfig

@AppScope
@Component
abstract class AppConfig : GuiConfig, DaoConfig, ServiceConfig