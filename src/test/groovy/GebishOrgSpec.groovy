import listeners.BrowserStackReportingSpec

class GebishOrgSpec extends BrowserStackReportingSpec {

    def "can get to the current Book of Geb"() {
        when:
        to GebishOrgHomePage

        and:
        manualsMenu.open()

        then:
        manualsMenu.links[0].text().startsWith("current")

        when:
        manualsMenu.links[0].click()

        then:
        at TheBookOfGebPage
    }
}
