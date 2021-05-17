FROM python:3

COPY requirements.txt .
RUN pip install --no-cache-dir --upgrade pip && \
    pip install --no-cache-dir -r requirements.txt

WORKDIR /app

ADD ./alarmserver .

CMD [ "python3", "alarmserver.py"]