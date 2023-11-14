import requests
from bs4 import BeautifulSoup

url = "https://www.youtube.com/watch?v=1-0mS2ZBrQk"
url = url.replace("youtube", "youpak")

html_doc = requests.get(url).text

# print(html_doc)

soup = BeautifulSoup(html_doc, 'html.parser')

a_tag = soup.find("div", {"class":"btn-group btn-group-justified"}).find_all("a")

hrefs = [href['href'] for href in a_tag]

if len(hrefs)>0:
    print(hrefs[0])
else:
    print("No video found")