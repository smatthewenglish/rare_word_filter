from scrapy.spider import BaseSpider
from scrapy.selector import HtmlXPathSelector
from scrapy.http import Request
from rare_word_filter.items import RareWordFilterItem
from urlparse import urljoin
from collections import OrderedDict

class FilterCreatorCrawler(BaseSpider):
    name = "filter_creator"
    allowed_domains = ["zdic.net"]
    start_urls = ['http://www.zdic.net/z/zb/cc1.htm']

    def parse(self, response):
        for url in response.xpath('//a/@href').extract():
            cb = self.parse if '/z/d' in url.lower() else self.parse_item
            yield Request(urljoin(response.url, url), callback=cb) 

    def parse_item(self, response):
        hxs = HtmlXPathSelector(response)
        item =  RareWordFilterItem()
        item["character"] = hxs.xpath('//*[@id="ziip"]/text()').extract()
        item["delimiter"] = "#######################"
        return item


