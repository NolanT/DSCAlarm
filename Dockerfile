FROM python:3.8

COPY requirements.txt .
RUN pip install --no-cache-dir --upgrade pip && \
    pip install --no-cache-dir -r requirements.txt

WORKDIR /app

ADD ./alarmserver .

ENV PYTHONUNBUFFERED=1
ENV PYTHONDONTWRITEBYTECODE=1

CMD [ "python3", "alarmserver.py", "-c", "/config/alarmserver.cfg"]
