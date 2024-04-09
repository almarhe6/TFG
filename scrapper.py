import psycopg2
import yfinance as yf
from datetime import datetime, timedelta

def get_yesterday_price(isin, conn):
    query = f"SELECT price FROM funds_historical WHERE isin = '{isin}' ORDER BY date DESC LIMIT 1"
    cursor = conn.cursor()
    cursor.execute(query)
    result = cursor.fetchone()
    cursor.close()
    return result[0] if result else None

conn = psycopg2.connect(
    dbname="tfg_db",
    user="alejandromartorellhernandez",
    password="",
    host="localhost"
)
cursor = conn.cursor()

query = "SELECT isin FROM funds"
cursor.execute(query)
funds = cursor.fetchall()

today = datetime.today()
yesterday = today - timedelta(days=10)

for row in funds:
    isin = row[0]
    print(isin)

    yesterday_price = get_yesterday_price(isin, conn)

    data = yf.download(isin, start=yesterday, end=today)
    if not data.empty:
        latest_price = data['Close'].iloc[-1]
        rent = (latest_price - yesterday_price) / yesterday_price * 100 if yesterday_price else 0
        insert_query = "INSERT INTO funds_historical (isin, date, price, rent) VALUES (%s, %s, %s, %s)"
        cursor.execute(insert_query, (isin, today, latest_price, rent))

# Commit los cambios y cierra la conexión
conn.commit()
conn.close()
